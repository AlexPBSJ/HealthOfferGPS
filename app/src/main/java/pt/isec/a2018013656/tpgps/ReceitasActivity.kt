package pt.isec.a2018013656.tpgps

import android.content.Intent
import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.ListView
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity

class ReceitasActivity: AppCompatActivity() {

    var listaReceitas = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receitas_layout)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaReceitas)
        val lista = findViewById<ListView>(R.id.lv_receitas)
        lista.adapter = adapter

        lista.setOnItemClickListener{parent, view, pos, id ->
            val intent = Intent(this, VerReceitaActivity::class.java)
            intent.apply {
                intent.putExtra("nome", listaReceitas.get(pos))
                intent.action = Intent.ACTION_VIEW
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }

    fun onAddReceitas(view: View) {
        val intent = Intent(this, AddReceitasActivity::class.java)
        startActivity(intent)
    }
}



