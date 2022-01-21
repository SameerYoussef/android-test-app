package com.example.testapp.unittests

import com.example.testapp.Engine
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

class EngineShould {

    private val engine = Engine(120, 200)

    @Test
    fun turnOn() = runTest {
        engine.turnOn()
        assertTrue(engine.isTurnedOn)
        assertEquals(95, engine.temp)
    }

    @Test
    fun tempRaisesUponIgnition() = runTest {
        engine.turnOn()
        assertEquals(95, engine.temp)
    }

    @Test
    fun turnOff() = runTest {
        engine.turnOff()
        assertEquals(15, engine.temp)
    }
}
