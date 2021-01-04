package pt.isec.a2018013656.tpgps

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.provider.Telephony.Mms.Part.FILENAME
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.io.*

const val TAG = "TAG PERFIL"
class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_layout)

        val edNome = findViewById<EditText>(R.id.edNomePerfil)
        val edIdade = findViewById<EditText>(R.id.edIdadePerfil)
        val edPeso = findViewById<EditText>(R.id.edPesoPerfil)
        val edAltura = findViewById<EditText>(R.id.edAlturaPerfil)
        val rbSexoM = findViewById<RadioButton>(R.id.rbSexoM)
        val rbSexoF = findViewById<RadioButton>(R.id.rbSexoF)

        edNome.isEnabled = false
        edIdade.isEnabled = false
        edPeso.isEnabled = false
        edAltura.isEnabled = false
        rbSexoF.isEnabled = false
        rbSexoM.isEnabled = false

        val bGuardaPerfil = findViewById<Button>(R.id.bGuardaPerfil)
        bGuardaPerfil.visibility = View.INVISIBLE

        lePerfil()

    }

    private fun lePerfil() {
        val edNome = findViewById<EditText>(R.id.edNomePerfil)
        val edIdade = findViewById<EditText>(R.id.edIdadePerfil)
        val edPeso = findViewById<EditText>(R.id.edPesoPerfil)
        val edAltura = findViewById<EditText>(R.id.edAlturaPerfil)
        val rbSexoM = findViewById<RadioButton>(R.id.rbSexoM)
        val rbSexoF = findViewById<RadioButton>(R.id.rbSexoF)


        val filename = FILENAME + "myProfile.txt"
        var content:String = filename.reader().toString()
        Log.i(TAG, "onCreate: $content")
        try {
            if (!isExternalStorageReadable()) {
                Log.i(TAG, "External Storage is not readable")
                return
            }
            val sb = mutableListOf<String>()
            val file = File(this?.getExternalFilesDir(null), filename)
            FileInputStream(file).bufferedReader().use {
                it?.forEachLine {
                    sb.add(it)
                }
            }
            edNome.setText(sb.get(0))
            edIdade.setText(sb.get(1))
            edPeso.setText(sb.get(2))
            edAltura.setText(sb.get(3))
            if(sb.get(4) == "Masculino")
                rbSexoM.isChecked = true
            else if(sb.get(4) == "Feminino")
                rbSexoF.isChecked = true
            

        } catch (e: IOException) {
            Log.i(TAG, e.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.edita_perfil_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.bEditaPerfil){
            Log.i(TAG, "onOptionsItemSelected: ENTRO AQUI POIS")
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

        outState?.putString("savedName", edNome.text.toString())
        outState?.putString("savedIdade", edIdade.text.toString())
        outState?.putString("savedPeso", edPeso.text.toString())
        outState?.putString("savedAltura", edAltura.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val edNome = findViewById<EditText>(R.id.edNomePerfil)
        val edIdade = findViewById<EditText>(R.id.edIdadePerfil)
        val edPeso = findViewById<EditText>(R.id.edPesoPerfil)
        val edAltura = findViewById<EditText>(R.id.edAlturaPerfil)

        edNome.setText(savedInstanceState?.getString("savedName"))
        edIdade.setText(savedInstanceState?.getString("savedIdade"))
        edPeso.setText(savedInstanceState?.getString("savedPeso"))
        edAltura.setText(savedInstanceState?.getString("savedAltura"))
    }

    private fun editaPerfil() {
        val edNome = findViewById<EditText>(R.id.edNomePerfil)
        val edIdade = findViewById<EditText>(R.id.edIdadePerfil)
        val edPeso = findViewById<EditText>(R.id.edPesoPerfil)
        val edAltura = findViewById<EditText>(R.id.edAlturaPerfil)
        val rbSexoM = findViewById<RadioButton>(R.id.rbSexoM)
        val rbSexoF = findViewById<RadioButton>(R.id.rbSexoF)

        edNome.isEnabled = true
        edIdade.isEnabled = true
        edPeso.isEnabled = true
        edAltura.isEnabled = true
        rbSexoF.isEnabled = true
        rbSexoM.isEnabled = true

        val bGuardaPerfil = findViewById<Button>(R.id.bGuardaPerfil)
        bGuardaPerfil.visibility = View.VISIBLE
    }

    fun onGuardaPerfil(view: View){
        val edNome = findViewById<EditText>(R.id.edNomePerfil)
        val edIdade = findViewById<EditText>(R.id.edIdadePerfil)
        val edPeso = findViewById<EditText>(R.id.edPesoPerfil)
        val edAltura = findViewById<EditText>(R.id.edAlturaPerfil)
        val rbSexoM = findViewById<RadioButton>(R.id.rbSexoM)
        val rbSexoF = findViewById<RadioButton>(R.id.rbSexoF)

        val fileName = FILENAME + "myProfile.txt"
        val sexo:String
        if(rbSexoM.isChecked){
            sexo = "Masculino"
        }else if(rbSexoF.isChecked){
            sexo = "Feminino"
        }else
            sexo = "Outro"

        try {
            if (isExternalStorageWritable()) {
                val file = File(this?.getExternalFilesDir(null), fileName)
                file.delete()
                val ficheiroOutput = FileOutputStream(file, true)
                ficheiroOutput.use {
                    val ps = PrintStream(it)
                    ps.println("${edNome.text.toString()}")
                    ps.println("${edIdade.text.toString()}")
                    ps.println("${edPeso.text.toString()}")
                    ps.println("${edAltura.text.toString()}")
                    ps.println("$sexo")
                }

            }
            Toast.makeText(this, "Perfil guardado com sucesso", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            Toast.makeText(this, "ERRO AO GUARDAR PERFIL!", Toast.LENGTH_LONG).show()
        }
            //fileObject.writeText("${edNome.text.toString()}\n${edIdade.text.toString()}\n${edPeso.text.toString()}\n${edAltura.text.toString()}")

        edNome.isEnabled = false
        edIdade.isEnabled = false
        edPeso.isEnabled = false
        edAltura.isEnabled = false
        rbSexoF.isEnabled = false
        rbSexoM.isEnabled = false

        val bGuardaPerfil = findViewById<Button>(R.id.bGuardaPerfil)
        bGuardaPerfil.visibility = View.INVISIBLE
    }

    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    fun onNotificacoes(view: View) {
        val intent = Intent(this, Notificacoes::class.java)
        startActivity(intent)
    }
}