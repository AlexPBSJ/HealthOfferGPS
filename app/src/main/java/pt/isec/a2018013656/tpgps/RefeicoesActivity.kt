package pt.isec.a2018013656.tpgps

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class RefeicoesActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refeicoes)
    }

    fun onJantar(view: View) {}
    fun onLanche(view: View) {}
    fun onAlmoco(view: View) {}
    fun onPequenoAlmoco(view: View) {}


}
