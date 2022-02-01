package com.example.testapp.unittests.car

import com.example.testapp.Engine
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

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
