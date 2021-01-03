package pt.isec.a2018013656.tpgps

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log

import android.widget.ArrayAdapter
import android.widget.ListView
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import java.io.File
import java.io.IOException

class ReceitasActivity: AppCompatActivity() {

    var listaReceitas = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receitas_layout)

        leReceitas()

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaReceitas)
        val lista = findViewById<ListView>(R.id.lv_receitas)
        lista.adapter = adapter

        lista.setOnItemClickListener{parent, view, pos, id ->
            val intent = Intent(this, VerReceitaActivity::class.java)
            intent.apply {
                intent.action = Intent.ACTION_VIEW
            }
            if (intent.resolveActivity(packageManager) != null) {
                intent.putExtra("nomeReceita", listaReceitas.get(pos))
                startActivity(intent)
            }
        }
    }

    private fun leReceitas(){
            val filepath = "${getExternalFilesDir(null)}/Receitas"
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
                            listaReceitas.add(currentFile.name.substringBefore('.'))
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

    fun onAddReceitas(view: View) {
        val intent = Intent(this, AddReceitasActivity::class.java)
        startActivity(intent)
    }

    fun onAddPinoquios(view: View) {
        val intent = Intent(this, AddReceitasActivity::class.java)
        startActivity(intent)
    }
}



