package com.example.introkotlin_901.temasKotlin

abstract class Figura {
    abstract fun calcularArea(): Double
}

class Circulo(private val radio: Double) : Figura() {
    override fun calcularArea(): Double {
        return Math.PI * radio * radio
    }
}

class Cuadrado(private val lado: Double) : Figura() {
    override fun calcularArea(): Double {
        return lado * lado
    }
}

class Triangulo(private val base: Double, private val altura: Double) : Figura() {
    override fun calcularArea(): Double {
        return (base * altura) / 2
    }
}

class Pentagono(private val lado: Double, private val apotema: Double) : Figura() {
    override fun calcularArea(): Double {
        val perimetro = 5 * lado
        return (perimetro * apotema) / 2
    }
}

fun main() {
    while (true) {
        println("\nSeleccione la figura para calcular el área:")
        println("1. Círculo")
        println("2. Cuadrado")
        println("3. Triángulo")
        println("4. Pentágono regular")
        println("5. Salir")
        print("Opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Ingrese el radio del círculo: ")
                val radio = readLine()?.toDoubleOrNull()
                if (radio != null && radio > 0) {
                    val circulo = Circulo(radio)
                    println("Área del círculo: %.2f".format(circulo.calcularArea()))
                } else {
                    println("Radio no válido.")
                }
            }

            2 -> {
                print("Ingrese el lado del cuadrado: ")
                val lado = readLine()?.toDoubleOrNull()
                if (lado != null && lado > 0) {
                    val cuadrado = Cuadrado(lado)
                    println("Área del cuadrado: %.2f".format(cuadrado.calcularArea()))
                } else {
                    println("Lado no válido.")
                }
            }

            3 -> {
                print("Ingrese la base del triángulo: ")
                val base = readLine()?.toDoubleOrNull()
                print("Ingrese la altura del triángulo: ")
                val altura = readLine()?.toDoubleOrNull()
                if (base != null && altura != null && base > 0 && altura > 0) {
                    val triangulo = Triangulo(base, altura)
                    println("Área del triángulo: %.2f".format(triangulo.calcularArea()))
                } else {
                    println("Base o altura no válidas.")
                }
            }

            4 -> {
                print("Ingrese el lado del pentágono: ")
                val lado = readLine()?.toDoubleOrNull()
                print("Ingrese la apotema: ")
                val apotema = readLine()?.toDoubleOrNull()
                if (lado != null && apotema != null && lado > 0 && apotema > 0) {
                    val pentagono = Pentagono(lado, apotema)
                    println("Área del pentágono: %.2f".format(pentagono.calcularArea()))
                } else {
                    println("Lado o apotema no válidos.")
                }
            }

            5 -> {
                println("Saliendo del programa...")
                break
            }

            else -> println("Opción no válida. Intente nuevamente.")
        }
    }
}