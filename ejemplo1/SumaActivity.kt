package com.example.introkotlin_901.ejemplo1

import android.os.Bundle
import android.widget.EditText
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.introkotlin_901.R

class SumaActivity : AppCompatActivity() {
    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var tv1: TextView
    private lateinit var rgOperaciones: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_suma)

        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        tv1 = findViewById(R.id.tv1)
        rgOperaciones = findViewById(R.id.rgOperaciones)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calcular(view: View) {
        val num1 = et1.text.toString().toDoubleOrNull()
        val num2 = et2.text.toString().toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Ingrese ambos números", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = when (rgOperaciones.checkedRadioButtonId) {
            R.id.rbSuma -> num1 + num2
            R.id.rbResta -> num1 - num2
            R.id.rbMultiplicacion -> num1 * num2
            R.id.rbDivision -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "No se puede dividir por 0", Toast.LENGTH_SHORT).show()
                    return
                }
                num1 / num2
            }
            else -> {
                Toast.makeText(this, "Seleccione una operación", Toast.LENGTH_SHORT).show()
                return
            }
        }

        tv1.text = "Resultado = $resultado"
    }
}