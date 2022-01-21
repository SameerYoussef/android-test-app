package com.example.testapp

import android.util.Log
import kotlinx.coroutines.delay

class Engine(
    val cc: Int,
    val hp: Int,
    var temp: Int = 15,
    var isTurnedOn: Boolean = false
) {
    suspend fun turnOn() {
        isTurnedOn = true
        delay(6000)
        Log.d("❌", "Finally 6s passed")
        temp = 95
    }

    fun turnOff() {
        isTurnedOn = false
        temp = 15
    }
}
