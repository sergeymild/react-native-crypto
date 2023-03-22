package com.crypto

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class CryptoModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  fun sha256Hash(value: String, promise: Promise) {
    promise.resolve(sha256Hash(value))
  }

  @ReactMethod
  fun blowfishEncrypt(value: String, key: String, promise: Promise) {
    promise.resolve(blowfishEncrypt(value, key))
  }

  @ReactMethod
  fun blowfishDecrypt(value: String, key: String, promise: Promise) {
    promise.resolve(blowfishDecrypt(value, key))
  }

  companion object {
    const val NAME = "Crypto"
  }
}
