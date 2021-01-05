package pt.isec.a2018013656.tpgps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.PrintStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alimentosDef = resources.getStringArray(R.array.alimentos_array)
        for (a in alimentosDef)
            adicionaAlimentosDef(a)
    }

    fun adicionaAlimentosDef (alimento :String){
        val nome = alimento.substringBefore(';')
        val calorias = alimento.substringAfter(';').substringBefore(':')
        val categoria = alimento.substringAfter(':')

        val fileName = nome + ".txt"
        val makesFile = File("${getExternalFilesDir(null)}/Alimentos/${categoria}").mkdirs()
        val filePath = "${getExternalFilesDir(null)}/Alimentos/${categoria}/"

        try {
            if (isExternalStorageWritable()) {
                Log.i(TAG, "onAdicionaAlimento: ")
                val file = File("${filePath}${fileName}")
                file.delete()
                val ficheiroOutput = FileOutputStream(file, true)
                ficheiroOutput.use {
                    val ps = PrintStream(it)
                    ps.println("${nome}")
                    ps.println("${calorias}")
                    ps.println("${categoria}")
                }

            }
        } catch (e: IOException) {
            Log.i(TAG, "adicionaAlimentosDef: ${e}")
        }
    }

    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun onVisualizaPerfil(view: View) {
        val intent = Intent(this, PerfilActivity::class.java)
        startActivity(intent)
    }


    fun onVisualizaRefeicoes(view: View) {
        val intent = Intent(this, RefeicoesActivity::class.java)
        startActivity(intent)
    }


    fun onVisualizaAlimentos(view: View) {
        val intent = Intent(this, AlimentosActivity::class.java)
        startActivity(intent)
    }

    fun onVisualizaReceitas(view: View) {
        val intent = Intent(this, ReceitasActivity::class.java)
        startActivity(intent)
    }


}