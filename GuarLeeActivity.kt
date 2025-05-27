package com.example.introkotlin_901.archivo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.FileNotFoundException
import java.io.IOException
import com.example.introkotlin_901.R

class GuarLeeActivity : AppCompatActivity() {
    private val fileName = "datos.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guar_lee)
        val inputText = findViewById<EditText>(R.id.inputText)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val readButton = findViewById<Button>(R.id.readButton)
        val btnBorra = findViewById<Button>(R.id.btnBorrar)
        val outputText = findViewById<TextView>(R.id.outputText)

        btnBorra.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmar")
            builder.setMessage("¿Estás seguro de que quieres borrar el contenido del archivo?")

            builder.setPositiveButton("Sí") { _, _ ->
                try {
                    openFileOutput("datos.txt", MODE_PRIVATE).use {
                        it.write("".toByteArray())
                    }
                    Toast.makeText(this, "Archivo borrado", Toast.LENGTH_SHORT).show()
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error al borrar", Toast.LENGTH_SHORT).show()
                }
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()

        }

        saveButton.setOnClickListener {
            val texto = inputText.text.toString()+"\n"
            try {
                openFileOutput(fileName, MODE_APPEND).use {
                    it.write(texto.toByteArray())
                }
                inputText.text.clear()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        readButton.setOnClickListener {
            try {
                val contenido = openFileInput(fileName).bufferedReader().useLines { lines ->
                    lines.joinToString("\n")
                }
                outputText.text = contenido
            } catch (e: FileNotFoundException) {
                outputText.text = "Archivo no encontrado"
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}