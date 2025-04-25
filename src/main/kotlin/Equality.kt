package main.kotlin

fun main() {

    // these all just depend on value because Int is a data-like structure
    val a = 3
    val b = a
    println("does b=a imply a==b? ${a==b}")
    println("does b=a imply a===b? ${a===b}")

    val c = Array(5){it}

    val d = c[0]
    println("does d=c[0] imply d==c[0]? ${d==c[0]}")
    println("does d=c[0] imply d===c[0]? ${d===c[0]}")
    println("d = c[0] = $d. Now we change c[0] to 5")
    c[0] = 5
    println("d= $d")
    println("does d=c[0] imply d==c[0]? ${d==c[0]}")
    println("does d=c[0] imply d===c[0]? ${d===c[0]}")




}