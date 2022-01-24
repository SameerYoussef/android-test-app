package com.example.testapp

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Engine(
    val cc: Int,
    val hp: Int,
    var temp: Int = 15,
    var isTurnedOn: Boolean = false
) {
    suspend fun turnOn() : Flow<Int> {
        isTurnedOn = true
        return flow {
            temp = 20
            (1..3).forEach { _ ->
                delay(2000)
                temp += 25
                emit(temp)
            }

            Log.d("❌", "Finally 6s passed")
        }

    }

    fun turnOff() {
        isTurnedOn = false
        temp = 15
    }
}
