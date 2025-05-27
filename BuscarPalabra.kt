package com.example.introkotlin_901.diccionario

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.introkotlin_901.R
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader

class BuscarPalabra : AppCompatActivity() {
    private val FILE_NAME = "diccionario.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buscar_palabra)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val etBuscar = findViewById<EditText>(R.id.etPalabraBuscar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        findViewById<Button>(R.id.btnBuscar).setOnClickListener {
            val palabra = etBuscar.text.toString().trim()
            if (palabra.isEmpty()) {
                Toast.makeText(this, "Ingresa una palabra", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Selecciona un idioma", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val buscarDeInglesAEspanol = selectedId == R.id.rbEspanol
            buscarPalabra(palabra, buscarDeInglesAEspanol)
        }

        findViewById<Button>(R.id.btnRegresar).setOnClickListener {
            finish()
        }
    }

    private fun buscarPalabra(palabra: String, buscarDeInglesAEspanol: Boolean) {
        try {
            val fileInputStream = openFileInput(FILE_NAME)
            val reader = BufferedReader(InputStreamReader(fileInputStream))
            var linea: String?
            var encontrada = false
            var resultado = ""

            while (reader.readLine().also { linea = it } != null) {
                val partes = linea?.split(",")
                if (partes?.size == 2) {
                    val esp = partes[0].trim()
                    val ing = partes[1].trim()

                    // Lógica corregida:
                    if (buscarDeInglesAEspanol) {
                        // Si seleccionó español, busca la palabra en inglés y devuelve español
                        if (ing.equals(palabra, ignoreCase = true)) {
                            resultado = esp
                            encontrada = true
                            break
                        }
                    } else {
                        // Si seleccionó inglés, busca la palabra en español y devuelve inglés
                        if (esp.equals(palabra, ignoreCase = true)) {
                            resultado = ing
                            encontrada = true
                            break
                        }
                    }
                }
            }
            reader.close()

            val tvResultado = findViewById<TextView>(R.id.tvResultado)
            if (encontrada) {
                tvResultado.text = "palabra encontrada\n\n$resultado"
            } else {
                tvResultado.text = "palabra no encontrada"
            }
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "No hay palabras guardadas aún", Toast.LENGTH_SHORT).show()
            findViewById<TextView>(R.id.tvResultado).text = "palabra no encontrada"
        } catch (e: Exception) {
            Toast.makeText(this, "Error al buscar: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}