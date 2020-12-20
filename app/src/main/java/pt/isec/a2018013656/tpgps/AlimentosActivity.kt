package pt.isec.a2018013656.tpgps

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class AlimentosActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activivty_alimentos_layout)
    }

    fun onAddAlimentos(view: View) {
        val intent = Intent(this, AddAlimentosActivity::class.java)
        startActivity(intent)
    }
}