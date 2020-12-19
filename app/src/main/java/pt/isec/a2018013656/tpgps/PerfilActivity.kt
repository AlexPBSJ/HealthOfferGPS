package pt.isec.a2018013656.tpgps

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.InputStream

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_layout)
/**
        val file = File("myProfile.txt")
        val inputStream: InputStream = File("myProfile.txt").inputStream()
        val lineList = mutableListOf<String>()

        if(file.exists()){
            inputStream.bufferedReader().forEachLine { lineList.add(it) }
            val edNome = findViewById<EditText>(R.id.edNomePerfil)
            val edIdade = findViewById<EditText>(R.id.edIdadePerfil)
            val edPeso = findViewById<EditText>(R.id.edPesoPerfil)
            val edAltura = findViewById<EditText>(R.id.edAlturaPerfil)
            edNome.setText(lineList.get(0))
            edIdade.setText(lineList.get(1))
            edPeso.setText(lineList.get(2))
            edAltura.setText(lineList.get(3))
        }
        */
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.edita_perfil_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.menu.edita_perfil_menu){
            editaPerfil()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val edNome = findViewById<EditText>(R.id.edNomePerfil)
        val edIdade = findViewById<EditText>(R.id.edIdadePerfil)
        val edPeso = findViewById<EditText>(R.id.edPesoPerfil)
        val edAltura = findViewById<EditText>(R.id.edAlturaPerfil)

        outState?.putString("savedName", edNome.text.toString()) ?: ""
        outState?.putInt("savedIdade", edIdade.text.toString().toInt()) ?: ""
        outState?.putFloat("savedPeso", edPeso.text.toString().toFloat()) ?: ""
        outState?.putFloat("savedAltura", edAltura.text.toString().toFloat()) ?: ""
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val edNome = findViewById<EditText>(R.id.edNomePerfil)
        val edIdade = findViewById<EditText>(R.id.edIdadePerfil)
        val edPeso = findViewById<EditText>(R.id.edPesoPerfil)
        val edAltura = findViewById<EditText>(R.id.edAlturaPerfil)

        edNome.setText(savedInstanceState?.getString("savedName"))
        edIdade.setText(savedInstanceState?.getInt("savedIdade").toString())
        edPeso.setText(savedInstanceState?.getFloat("savedPeso").toString())
        edAltura.setText(savedInstanceState?.getFloat("savedAltura").toString())
    }

    private fun editaPerfil() {
        val edNome = findViewById<EditText>(R.id.edNomePerfil)
        val edIdade = findViewById<EditText>(R.id.edIdadePerfil)
        val edPeso = findViewById<EditText>(R.id.edPesoPerfil)
        val edAltura = findViewById<EditText>(R.id.edAlturaPerfil)

        edNome.isEnabled = true
        edIdade.isEnabled = true
        edPeso.isEnabled = true
        edAltura.isEnabled = true
    }

    fun onGuardaPerfil(view: View){
        val edNome = findViewById<EditText>(R.id.edNomePerfil)
        val edIdade = findViewById<EditText>(R.id.edIdadePerfil)
        val edPeso = findViewById<EditText>(R.id.edPesoPerfil)
        val edAltura = findViewById<EditText>(R.id.edAlturaPerfil)

        val filename = "myProfile.txt"
        val myFile = File(filename)

        myFile.printWriter().use { out->
            out.println(edNome.text.toString())
            out.println(edIdade.text.toString())
            out.println(edPeso.text.toString())
            out.println(edAltura.text.toString())
        }

        edNome.isEnabled = false
        edIdade.isEnabled = false
        edPeso.isEnabled = false
        edAltura.isEnabled = false

        Toast.makeText(this, "Perfil guardado com sucesso", Toast.LENGTH_LONG).show()
    }
}