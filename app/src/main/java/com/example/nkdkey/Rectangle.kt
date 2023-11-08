package com.example.nkdkey

data class Rectangle(
    private val width: Long,
    private val height: Long
) {

    fun calculateSquare() = width * height

}