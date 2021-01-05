package pt.isec.a2018013656.tpgps

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.Telephony
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class AddReceitasActivity : AppCompatActivity() {
    var sb = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_receitas)

        Log.i(TAG, "onCreate: POR LER")
        leAlimentos()
        Log.i(TAG, "onCreate: LEU")

        val refeicoes = resources.getStringArray(R.array.refeicoes_array)

        val spinnerRefeicao = findViewById<Spinner>(R.id.spinner_refeicoes)
        val adapterRefeicao = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, refeicoes
        )
        spinnerRefeicao.adapter = adapterRefeicao

        spinnerRefeicao.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        val spinner = findViewById<Spinner>(R.id.spinner_alimento_existente)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, sb)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

    }

    private fun leAlimentos() {
        val filepath = "${getExternalFilesDir(null)}/Alimentos"
        var listAllFiles = File(filepath).walkTopDown()

        try {
            if (!isExternalStorageReadable()) {
                Log.i(TAG, "External Storage is not readable")
                return
            }

            if (listAllFiles != null) {
                for (currentFile in listAllFiles) {
                    if (currentFile.name.endsWith(".txt")) {
                        Log.e("downloadFileName", currentFile.name)
                        sb.add(currentFile.name.substringBefore('.'))
                    }
                }
            }

        } catch (e: IOException) {
            Log.i(TAG, e.toString())
        }
    }

    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.guarda_receita, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.bGuardaReceita){
            Log.i(TAG, "onOptionsItemSelected: ENTRO AQUI POIS")
            guardaReceita()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun guardaReceita(){
        onBackPressed()
        Toast.makeText(this, "Receita guardada com sucesso", Toast.LENGTH_LONG).show()
    }

    private fun getCalorias (nome: String) : Int{
        val filepath = "${getExternalFilesDir(null)}/Alimentos"
        var listAllFiles = File(filepath).walkTopDown()

        val sb = mutableListOf<String>()

        try {
            if (!isExternalStorageReadable()) {
                Log.i(TAG, "External Storage is not readable")
                return 0
            }

            if (listAllFiles != null) {
                for (currentFile in listAllFiles) {
                    if (currentFile.name.substringBefore('.') == nome) {
                        val file = File(currentFile.absolutePath)
                        FileInputStream(file).bufferedReader().use {
                            it?.forEachLine {
                                sb.add(it)
                            }
                        }
                        //Log.e("downloadFileName", currentFile.name)
                    }
                }
            }

        } catch (e: IOException) {
            Log.i(TAG, e.toString())
        }

        return sb.get(1).toInt()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, ReceitasActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onAdicionaAlimentoReceita(view: View){
        val etNomeR = findViewById<EditText>(R.id.etNomeReceita)
        val spinnerAlimento = findViewById<Spinner>(R.id.spinner_alimento_existente)
        val etQuantidade = findViewById<EditText>(R.id.editTextQuantidade)
        val spinnerRefeicao = findViewById<Spinner>(R.id.spinner_refeicoes)

        val aux = etNomeR.text.toString()
        etNomeR.setText(aux.capitalize())

        val calorias = getCalorias(spinnerAlimento.selectedItem.toString())

        val file = File("${getExternalFilesDir(null)}/Receitas").mkdir()
        val fileName = "${getExternalFilesDir(null)}/Receitas/${etNomeR.text.toString()}.txt"

        try {
            if (isExternalStorageWritable()) {
                val file = File(fileName)
                val ficheiroOutput = FileOutputStream(file, true)
                ficheiroOutput.use {
                    val ps = PrintStream(it)
                    if(etNomeR.isEnabled){
                        ps.println("${spinnerRefeicao.selectedItem.toString()}")
                        spinnerRefeicao.isEnabled = false
                    }
                    ps.println("${spinnerAlimento.selectedItem.toString()} ${etQuantidade.text.toString()} ; ${calorias}")
                }

            }
            Toast.makeText(this, "Alimento adicionado", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "ERRO AO ADICIONAR ALIMENTO!", Toast.LENGTH_LONG).show()
        }
        etNomeR.isEnabled = false
    }
    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

}