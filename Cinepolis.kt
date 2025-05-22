package com.example.introkotlin_901.cinepolis

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.introkotlin_901.R
import java.text.NumberFormat
import java.util.Locale

class Cinepolis : AppCompatActivity() {

    private lateinit var inputCustomerName: EditText
    private lateinit var inputBuyersCount: EditText
    private lateinit var inputTicketsCount: EditText
    private lateinit var radioYes: RadioButton
    private lateinit var textTotalAmount: TextView
    private lateinit var btnCalculate: Button

    private val TICKET_PRICE = 12.0
    private val MAX_TICKETS_PER_PERSON = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinepolis)

        initViews()
        setupButton()
    }

    private fun initViews() {
        inputCustomerName = findViewById(R.id.inputCustomerName)
        inputBuyersCount = findViewById(R.id.inputBuyersCount)
        inputTicketsCount = findViewById(R.id.inputTicketsCount)
        radioYes = findViewById(R.id.radioYes)
        textTotalAmount = findViewById(R.id.textTotalAmount)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun setupButton() {
        btnCalculate.setOnClickListener {
            calculateTotalPayment()
        }
    }

    private fun calculateTotalPayment() {
        val customerName = inputCustomerName.text.toString().trim()
        if (customerName.isEmpty()) {
            inputCustomerName.error = "Ingrese un nombre"
            return
        }

        val buyersText = inputBuyersCount.text.toString()
        val ticketsText = inputTicketsCount.text.toString()

        if (buyersText.isEmpty() || ticketsText.isEmpty()) {
            Toast.makeText(this, "Campo Obligatorio", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val buyers = buyersText.toInt()
            val tickets = ticketsText.toInt()

            if (buyers < 1) {
                inputBuyersCount.error = "Deben de ser al menos 1 persona"
                return
            }

            if (tickets < 1) {
                inputTicketsCount.error = "Debe comprar al menos 1 boleto"
                return
            }

            val maxTickets = buyers * MAX_TICKETS_PER_PERSON
            if (tickets > maxTickets) {
                inputTicketsCount.error = "Máximo $maxTickets boletos para $buyers personas"
                return
            }

            var total = calculateFinalPrice(tickets, radioYes.isChecked)
            displayTotalAmount(total)
            showSummary(customerName, tickets, total)

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Campo Obligatorio", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateFinalPrice(tickets: Int, hasCinecoCard: Boolean): Double {
        var total = tickets * TICKET_PRICE


        total = when {
            tickets > 5 -> total * 0.85
            tickets >= 3 -> total * 0.90
            else -> total
        }


        if (hasCinecoCard) {
            total *= 0.90
        }

        return total
    }

    private fun displayTotalAmount(total: Double) {
        val format = NumberFormat.getCurrencyInstance(Locale("es", "MX"))
        format.maximumFractionDigits = 2
        textTotalAmount.text = format.format(total)
    }

    private fun showSummary(customerName: String, tickets: Int, total: Double) {
        val summary = """
            Cliente: $customerName
            Boletos: $tickets
            Total: ${"%.2f".format(total)}
        """.trimIndent()

        Toast.makeText(this, summary, Toast.LENGTH_LONG).show()
    }
}