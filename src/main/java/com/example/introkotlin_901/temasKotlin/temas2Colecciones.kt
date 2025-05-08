package com.example.introkotlin_901.temasKotlin

fun main(){
    /*
	List, MutableList
	Set MutableList
	Map, MutableMap
	*/

    var readOnlyList:List<String> = listOf("Lunes","Martes","Miercoles","Jueves","Vierenes")

    println(readOnlyList)
    println("El primer dia es ${readOnlyList[0]}")
    println("El primer dia es ${readOnlyList.first()}")
    println("El numero de dias es ${readOnlyList.size}")
    println("Martes" in readOnlyList)
    var figuras:MutableList<String> = mutableListOf("Circula","Cuadrado","Triangulo")
    println(figuras)
    figuras.add("Pentagono")
    figuras.removeAt(0)
    println(figuras)
    figuras.remove("Cuadrado4")
    println(figuras)

    val readOnlyFrutas = mapOf("manzana" to 1500, "platano" to 2000, "sandia" to 2500)
    println(readOnlyFrutas)
    println(readOnlyFrutas.keys)
    println(readOnlyFrutas.values)
}