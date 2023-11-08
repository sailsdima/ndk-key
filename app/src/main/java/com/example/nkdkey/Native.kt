package com.example.nkdkey

class Native {

    external fun calculateSquare(width: Long, height: Long): Long

    external fun getMapsKey(): String

    external fun getEncryptedMapsKey(): String

    companion object {
        init {
            System.loadLibrary("mycppmodule")
        }
    }
}