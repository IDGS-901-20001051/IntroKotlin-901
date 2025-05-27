package com.example.introkotlin_901.diccionario

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.introkotlin_901.R

class AgregarPalabra : AppCompatActivity() {
    private val FILE_NAME = "diccionario.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_palabra)

        findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            guardarPalabra()
        }

        findViewById<Button>(R.id.btnRegresar).setOnClickListener {
            finish()
        }
    }

    private fun guardarPalabra() {
        val palabraEsp = findViewById<EditText>(R.id.etEspanol).text.toString().trim()
        val palabraIng = findViewById<EditText>(R.id.etIngles).text.toString().trim()

        if (palabraEsp.isEmpty() || palabraIng.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa ambas palabras", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val fileOutputStream = openFileOutput(FILE_NAME, MODE_APPEND)
            fileOutputStream.write("$palabraEsp,$palabraIng\n".toByteArray())
            fileOutputStream.close()

            Toast.makeText(this, "Palabras guardadas con éxito", Toast.LENGTH_SHORT).show()
            findViewById<EditText>(R.id.etEspanol).text.clear()
            findViewById<EditText>(R.id.etIngles).text.clear()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}