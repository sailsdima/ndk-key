package com.example.nkdkey

import android.util.Base64
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


object Encryptor {

    fun encryptInitial() {
        val encryptionKey = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        val dataToEncrypt = "Key for maps"

        val aes128Key: ByteArray = generateAES128Key(encryptionKey)
        val dataBytes: ByteArray = dataToEncrypt.toByteArray()

        val encryptedData: ByteArray = encrypt(aes128Key, dataBytes)!!
        val encryptedKey: String = Base64.encodeToString(encryptedData, Base64.DEFAULT)

        println("Encrypted key $encryptedKey")
    }

    fun getMapsKey(): String {
        val encryptionKey = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        val encryptedKey = Native().getEncryptedMapsKey()
        val encryptedKeyBytes: ByteArray = Base64.decode(encryptedKey, Base64.DEFAULT)

        val aes128Key: ByteArray = generateAES128Key(encryptionKey)
        val decryptedData = decrypt(aes128Key, encryptedKeyBytes)

        val decryptedKey = String(decryptedData)

        println("Decrypted key $decryptedKey")

        return decryptedKey
    }

    @Throws(java.lang.Exception::class)
    fun encrypt(key: ByteArray, value: ByteArray): ByteArray {
        val secureRandom = SecureRandom()
        val iv = ByteArray(12)
        secureRandom.nextBytes(iv)
        val skeySpec = SecretKeySpec(key, "AES")
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val gcmParameterSpec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, gcmParameterSpec)
        val encrypted = cipher.doFinal(value)
        return iv + encrypted
    }

    @Throws(java.lang.Exception::class)
    fun decrypt(key: ByteArray, value: ByteArray): ByteArray {
        val iv = ByteArray(12)
        System.arraycopy(value, 0, iv, 0, 12)
        val skeySpec = SecretKeySpec(key, "AES")
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val gcmParameterSpec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, gcmParameterSpec)
        return cipher.doFinal(value, 12, value.size - 12)
    }

    private fun generateAES128Key(key: String): ByteArray {
        val salt = ByteArray(16)

        val keyLength = 128
        val iterationCount = 1000
        val keySpec: KeySpec = PBEKeySpec(key.toCharArray(), salt, iterationCount, keyLength)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        return factory.generateSecret(keySpec).encoded
    }

}