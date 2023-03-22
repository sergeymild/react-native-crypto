package com.crypto

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


private fun bytesToHexString(bytes: ByteArray): String? {
  // http://stackoverflow.com/questions/332079
  val sb = StringBuffer()
  for (i in bytes.indices) {
    val hex = Integer.toHexString(0xFF and bytes[i].toInt())
    if (hex.length == 1) {
      sb.append('0')
    }
    sb.append(hex)
  }
  return sb.toString()
}

fun sha256Hash(value: String): String? {
  val hash: String?
  return try {
    val digest = MessageDigest.getInstance("SHA-256")
    digest.update(value.toByteArray())
    hash = bytesToHexString(digest.digest())
    hash
  } catch (e1: NoSuchAlgorithmException) {
    // TODO Auto-generated catch block
    e1.printStackTrace()
    null
  }
}



fun blowfishEncrypt(value: String, key: String): String {
  val secretKey = SecretKeySpec(key.toByteArray(), "Blowfish")
  val cipher = Cipher.getInstance("Blowfish/ECB/PKCS7Padding")
  cipher.init(Cipher.ENCRYPT_MODE, secretKey)
  val outputBytes: ByteArray = cipher.doFinal(value.toByteArray())
  return byteArrayToHexString(outputBytes)
}


fun hexStringToByteArray(hex: String): ByteArray {
  val xxx = if (hex.length % 2 !== 0) "0$hex" else hex
  val b = ByteArray(xxx.length / 2)
  for (i in b.indices) {
    val index = i * 2
    val v: Int = xxx.substring(index, index + 2).toInt(16)
    b[i] = v.toByte()
  }
  return b
}

val hexArray = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
fun byteArrayToHexString(bytes: ByteArray): String {
  val hexChars = CharArray(bytes.size * 2)
  var v: Int
  for (j in bytes.indices) {
    v = bytes[j].toInt() and 0xFF
    hexChars[j * 2] = hexArray[v ushr 4]
    hexChars[j * 2 + 1] = hexArray[v and 0x0F]
  }
  return String(hexChars)
}

fun blowfishDecrypt(value: String, key: String): String? {
  val decodeFromBase64 = hexStringToByteArray(value)
  try {
    val keyBytes: ByteArray = key.toByteArray()
    val skeySpec = SecretKeySpec(keyBytes, "Blowfish")
    val cipher = Cipher.getInstance("Blowfish/ECB/PKCS7Padding")
    cipher.init(Cipher.DECRYPT_MODE, skeySpec)
    val decrypted = cipher.doFinal(decodeFromBase64)
    return decrypted.toString(Charsets.UTF_8)
  } catch (e: Exception) {
    e.printStackTrace()
  }
  return null
}
