package pt.isec.a2018013656.tpgps

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class VerReceitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_receitas_layout)

        val nomeReceita = intent.getStringExtra("nomeReceita")
        var listaIngredientes = mutableListOf<String>()
        var caloriasTotais = 0

        val tvNome = findViewById<TextView>(R.id.tvNomeReceita)
        tvNome.setText(nomeReceita)
        Log.i(TAG, "onCreate: receita $nomeReceita")

        val filepath = "${getExternalFilesDir(null)}/Receitas"
        var listAllFiles = File(filepath).walkTopDown()

        val sb = mutableListOf<String>()

        try {
            if (!isExternalStorageReadable()) {
                Log.i(TAG, "onCreate: receita External Storage is not readable")
                return
            }

            if (listAllFiles != null) {
                for (currentFile in listAllFiles) {
                    if (currentFile.name.substringBefore('.') == nomeReceita) {
                        Log.i(TAG, "onCreate: receita entra")
                        val file = File(currentFile.absolutePath)
                        FileInputStream(file).bufferedReader().use {
                            it?.forEachLine {
                                sb.add(it)
                            }
                        }
                    }
                }
            }

        } catch (e: IOException) {
            Log.i(TAG, e.toString())
        }

        for(x in sb){
            listaIngredientes.add(x.substringBefore(';'))
            caloriasTotais += x.substringAfter("; ").toInt()
        }

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaIngredientes)
        val lista = findViewById<ListView>(R.id.lv_ingredientes)
        lista.adapter = adapter

        val tvCaloriasReceita = findViewById<TextView>(R.id.tvCaloriasReceita)
        tvCaloriasReceita.setText(caloriasTotais.toString())
    }

    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }
}