package com.example.testapp.unittests

import com.example.testapp.Engine
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

class EngineShould {

    private val engine = Engine(120, 200)

    @Test
    fun turnOn() = runTest {
        engine.turnOn()
        assertTrue(engine.isTurnedOn)
    }

    @Test
    fun tempRaisesGraduallyUponIgnition() = runTest {
        val flow = engine.turnOn()
        val actual = flow.toList()
        assertEquals(listOf(45, 70, 95), actual)
    }

    @Test
    fun turnOff() = runTest {
        engine.turnOff()
        assertEquals(15, engine.temp)
    }
}
