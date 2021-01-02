package pt.isec.a2018013656.tpgps

import android.os.Bundle
import android.os.Environment
import android.provider.Telephony
import android.provider.Telephony.Mms.Part.FILENAME
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.PrintStream
import java.nio.file.Files.createDirectories
import java.nio.file.Files.createDirectory

class AddAlimentosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_alimentos_layout)

        val categorias = resources.getStringArray(R.array.categorias_array)

        val spinner: Spinner = findViewById(R.id.spinner_categorias)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, categorias
        )
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    fun onAdicionaAlimento (view: View){
        val etNome = findViewById<EditText>(R.id.etNomeAlimento)
        val spinnerCat = findViewById<Spinner>(R.id.spinner_categorias)
        val etCalorias = findViewById<EditText>(R.id.etCaloriasAlimento)

        if(etNome.text.length < 1 || etCalorias.text.length < 1){
            Toast.makeText(this, "Por favor preencha todos os campos!", Toast.LENGTH_LONG).show()
            return
        }

        val fileName = etNome.text.toString() + ".txt"
        val makesFile = File("${getExternalFilesDir(null)}/Alimentos/${spinnerCat.selectedItem.toString()}").mkdirs()
        val filePath = "${getExternalFilesDir(null)}/Alimentos/${spinnerCat.selectedItem.toString()}/"

        try {
            if (isExternalStorageWritable()) {
                Log.i(TAG, "onAdicionaAlimento: ")
                val file = File("${filePath}${fileName}")
                //val file = File(this?.getExternalFilesDir(null), fileName)
                file.delete()
                val ficheiroOutput = FileOutputStream(file, true)
                ficheiroOutput.use {
                    val ps = PrintStream(it)
                    ps.println("${etNome.text.toString()}")
                    ps.println("${etCalorias.text.toString()}")
                    ps.println("${spinnerCat.selectedItem.toString()}")
                }

            }
            Toast.makeText(this, "Alimento guardado com sucesso", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            Toast.makeText(this, "ERRO AO GUARDAR ALIMENTO!", Toast.LENGTH_LONG).show()
        }
    }


    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

}