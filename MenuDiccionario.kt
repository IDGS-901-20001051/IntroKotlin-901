package com.example.introkotlin_901.diccionario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.introkotlin_901.MenuActivity
import com.example.introkotlin_901.R

class MenuDiccionario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_diccionario)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnCapturar).setOnClickListener {
            navigateToCapturar()
        }

        findViewById<Button>(R.id.btnBuscar).setOnClickListener {
            navigateToBuscar()
        }

        findViewById<Button>(R.id.btnRegresar).setOnClickListener {
            finish()
        }
    }

    private fun navigateToCapturar() {
        val intent = Intent(this, AgregarPalabra::class.java)
        startActivity(intent)
    }

    private fun navigateToBuscar() {
        val intent = Intent(this, BuscarPalabra::class.java)
        startActivity(intent)
    }
}