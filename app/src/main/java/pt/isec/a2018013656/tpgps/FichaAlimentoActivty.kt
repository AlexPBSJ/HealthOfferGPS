package pt.isec.a2018013656.tpgps

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class FichaAlimentoActivty: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ficha_alimento_layout)

        val tvNome = findViewById<TextView>(R.id.tvNomeAlimento)
        val tvCalorias = findViewById<TextView>(R.id.tvCaloriasAlimento)
        val tvCategoria = findViewById<TextView>(R.id.tvCategoriaAlimento)
        val name = (intent.getStringExtra("nome"))

        val filepath = "${getExternalFilesDir(null)}/Alimentos"
        var listAllFiles = File(filepath).walkTopDown()

        val sb = mutableListOf<String>()

        try {
            if (!isExternalStorageReadable()) {
                Log.i(TAG, "External Storage is not readable")
                return
            }

            if (listAllFiles != null) {
                for (currentFile in listAllFiles) {
                    if (currentFile.name.substringBefore('.') == name) {
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

        tvNome.setText(sb.get(0))
        tvCalorias.setText(sb.get(1))
        tvCategoria.setText(sb.get(2))
    }

    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }
}