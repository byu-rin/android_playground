package com.example.practice

fun main() {
    println("Enter the first number:")
    val num1 = readLine()?.toIntOrNull() ?: return

    println("Enter the operator (+, -, *, /, %):")
    val operator = readLine()

    println("Enter the second number:")
    val num2 = readLine()?.toIntOrNull() ?: return

    val result = when (operator) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> num1 / num2
        "%" -> num1 % num2
        else -> {
            println("Invalid operator")
            return
        }
    }
    println("Result: $result")
}
