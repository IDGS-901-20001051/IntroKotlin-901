package com.example.introkotlin_901.temasKotlin
import kotlin.math.sqrt

fun main() {

    while (true) {
        println("\nIngrese los coeficientes:")
        val a = obtenerNumero("Ingrese el coeficiente a: ")
        val b = obtenerNumero("Ingrese el coeficiente b: ")
        val c = obtenerNumero("Ingrese el coeficiente c: ")

        if (a == 0.0) {
            println("\nERROR: El coeficiente 'a' no puede ser cero para una ecuación cuadrática.")
            println("Esto la convertiría en una ecuación lineal (bx + c = 0).")
            println("Por favor, ingrese los coeficientes nuevamente.")
            continue
        }

        val discriminante = b * b - 4 * a * c

        when {
            discriminante > 0 -> {
                val x1 = (-b + sqrt(discriminante)) / (2 * a)
                val x2 = (-b - sqrt(discriminante)) / (2 * a)
                println("\nSolución:")
                println("x₁ = (-(${formatearResultado(b)}) + √${formatearResultado(discriminante)}) / (2*${formatearResultado(a)}) = ${formatearResultado(x1)}")
                println("x₂ = (-(${formatearResultado(b)}) - √${formatearResultado(discriminante)}) / (2*${formatearResultado(a)}) = ${formatearResultado(x2)}")
            }
            discriminante == 0.0 -> {
                val x = -b / (2 * a)
                println("\nSolución:")
                println("x = -(${formatearResultado(b)}) / (2*${formatearResultado(a)}) = ${formatearResultado(x)} (raíz doble)")
            }
            else -> {
                val parteReal = -b / (2 * a)
                val parteImag = sqrt(-discriminante) / (2 * a)
                println("\nSolución:")
                println("x₁ = ${formatearResultado(parteReal)} + ${formatearResultado(parteImag)}i")
                println("x₂ = ${formatearResultado(parteReal)} - ${formatearResultado(parteImag)}i")
            }
        }
        break
    }
    println("\n--- Fin del programa ---")
}

fun obtenerNumero(mensaje: String): Double {
    while (true) {
        print(mensaje)
        val linea = readLine()
        if (linea == null) {
            println("\nEntrada finalizada. Saliendo.")
            throw RuntimeException("Entrada de usuario finalizada inesperadamente.")
        }
        val input = linea.trim().toDoubleOrNull()
        if (input != null) return input
        println("ERROR: Ingrese un número válido (ej. 5, -3.14)")
    }
}

fun formatearResultado(valor: Double): String {
    return if (valor % 1.0 == 0.0) {
        "%.0f".format(valor)
    } else {
        "%.4f".format(valor)
    }
}

fun formatearCoeficiente(coef: Double, variable: String, incluirSigno: Boolean = false): String {
    val valorFormateado = formatearResultado(coef.let { if (it == -0.0) 0.0 else it })
    return when {
        coef == 0.0 && variable.isNotEmpty() -> ""
        coef == 1.0 && variable.isNotEmpty() -> if (incluirSigno) "+ $variable" else variable
        coef == -1.0 && variable.isNotEmpty() -> if (incluirSigno) "- $variable" else "-$variable"
        coef > 0.0 && incluirSigno -> "+ $valorFormateado$variable"
        coef < 0.0 && incluirSigno -> "- ${formatearResultado(kotlin.math.abs(coef))}$variable"
        else -> "$valorFormateado$variable"
    }
}