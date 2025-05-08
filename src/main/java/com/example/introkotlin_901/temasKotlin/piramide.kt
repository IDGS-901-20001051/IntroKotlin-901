package com.example.introkotlin_901.temasKotlin

fun main() {
    var numero: Int

    do {
        print("Ingrese el número de niveles: ")
        numero = readLine()?.toIntOrNull() ?: 1

        if (numero > 0) {
            println("\nPirámide de $numero niveles:\n")
            var fila = 1

            do {
                var espacios = numero - fila
                do {
                    print(" ")
                    espacios--
                } while (espacios > 0)

                var asteriscos = 1
                do {
                    print("*")
                    if (asteriscos < fila) {
                        print(" ")
                    }
                    asteriscos++
                } while (asteriscos <= fila)

                println()
                fila++
            } while (fila <= numero)
        }
    } while (numero > 0)

    println("\nPrograma finalizado.")
}
