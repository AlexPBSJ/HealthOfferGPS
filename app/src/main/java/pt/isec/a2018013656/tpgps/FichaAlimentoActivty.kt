package pt.isec.a2018013656.tpgps

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FichaAlimentoActivty: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ficha_alimento_layout)

        val tv = findViewById<TextView>(R.id.tvNomeAlimento)
        tv.setText(intent.getStringExtra("nome"))

    }
}