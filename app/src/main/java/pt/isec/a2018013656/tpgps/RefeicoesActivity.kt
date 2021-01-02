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


    fun onAlmoco(view: View) {
        val intent = Intent(this, Refeicoes_AlmocoActivity::class.java)
        startActivity(intent)
    }
    fun onPequenoAlmoco(view: View) {
        val intent = Intent(this, Refeicoes_PequenoAlmocoActivity::class.java)
        startActivity(intent)
    }
    fun onJantar(view: View) {
        val intent = Intent(this, RefeicoesJantarActivity::class.java)
        startActivity(intent)
    }
    fun onLanche(view: View) {
        val intent = Intent(this, RefeicoesLancheActivity::class.java)
        startActivity(intent)
    }


}
