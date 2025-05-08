package com.example.introkotlin_901.temasKotlin

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
            1 -> calcularAreaCirculo()
            2 -> calcularAreaCuadrado()
            3 -> calcularAreaTriangulo()
            4 -> calcularAreaPentagono()
            5 -> {
                println("Saliendo del programa...")
                return
            }
            else -> println("Opción no válida. Por favor, seleccione 1-5.")
        }
    }
}

fun calcularAreaCirculo() {
    println("\nCálculo del área de un círculo")
    print("Ingrese el radio del círculo: ")
    val radio = readLine()?.toDoubleOrNull()

    if (radio != null && radio > 0) {
        val area = 3.1416f * radio * radio
        println("El área del círculo con radio $radio es: ${"%.2f".format(area)}")
    } else {
        println("Error: Radio no válido. Debe ser un número positivo.")
    }
}

fun calcularAreaCuadrado() {
    println("\nCálculo del área de un cuadrado")
    print("Ingrese la longitud de un lado: ")
    val lado = readLine()?.toDoubleOrNull()

    if (lado != null && lado > 0) {
        val area = lado * lado
        println("El área del cuadrado con lado $lado es: ${"%.2f".format(area)}")
    } else {
        println("Error: Longitud no válida. Debe ser un número positivo.")
    }
}

fun calcularAreaTriangulo() {
    println("\nCálculo del área de un triángulo")
    print("Ingrese la base del triángulo: ")
    val base = readLine()?.toDoubleOrNull()
    print("Ingrese la altura del triángulo: ")
    val altura = readLine()?.toDoubleOrNull()

    if (base != null && altura != null && base > 0 && altura > 0) {
        val area = (base * altura) / 2
        println("El área del triángulo con base $base y altura $altura es: ${"%.2f".format(area)}")
    } else {
        println("Error: Base o altura no válidas. Deben ser números positivos.")
    }
}

fun calcularAreaPentagono() {
    println("\nCálculo del área de un pentágono regular")
    print("Ingrese la longitud de un lado: ")
    val lado = readLine()?.toDoubleOrNull()
    print("Ingrese la apotema (distancia del centro al punto medio de un lado): ")
    val apotema = readLine()?.toDoubleOrNull()

    if (lado != null && apotema != null && lado > 0 && apotema > 0) {
        val perimetro = 5 * lado
        val area = (perimetro * apotema) / 2
        println("El área del pentágono regular con lado $lado y apotema $apotema es: ${"%.2f".format(area)}")
    } else {
        println("Error: Longitud del lado o apotema no válidas. Deben ser números positivos.")
    }
}