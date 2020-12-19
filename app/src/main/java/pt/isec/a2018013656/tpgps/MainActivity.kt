package pt.isec.a2018013656.tpgps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onVisualizaPerfil(view: View) {
        val intent = Intent(this, PerfilActivity::class.java)
        startActivity(intent)
    }

    fun onVisualizaRefeicoes(view: View) {
        val intent = Intent(this, RefeicoesActivity::class.java)
        startActivity(intent)
    }




}