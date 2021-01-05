package pt.isec.a2018013656.tpgps

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.Telephony
import android.provider.Telephony.Mms.Part.FILENAME
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class AlimentosActivity : AppCompatActivity(){
    var sb = mutableListOf<String>()
    lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activivty_alimentos_layout)

        leAlimentos()

        sb.sort()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sb)
        val lista = findViewById<ListView>(R.id.lv_alimentos)
        lista.adapter = adapter

        lista.setOnItemClickListener{parent, view, pos, id ->
            val intent = Intent(this, FichaAlimentoActivty::class.java)
            intent.apply {
                intent.action = Intent.ACTION_VIEW
            }
            if (intent.resolveActivity(packageManager) != null) {
                intent.putExtra("nome", sb.get(pos))
                startActivity(intent)
            }
        }

    }


    fun onAddAlimentos(view: View) {
        val intent = Intent(this, AddAlimentosActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun leAlimentos() {
        /*
        val defpath = "src/main/java/pt/isec/a2018013656/tpgps/alimentos"
        val listaFicheirosDef = File(defpath).walkTopDown()

        try {
            if (listaFicheirosDef != null) {
                Log.i(TAG, "leAlimentos: ${File(defpath).list()}")
                for (x in listaFicheirosDef) {
                    Log.e("downloadFileName1", x.toString())
                    if (x.toString().endsWith(".txt")) {
                        Log.e("downloadFileName1", x.toString())
                        sb.add(x.toString().substringBefore('.'))
                    }
                }
            }
        } catch (e: IOException) {
            Log.i(TAG, e.toString())
        }
        */

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
}