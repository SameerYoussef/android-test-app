package com.example.testapp.integrationtests

import com.example.testapp.Car
import com.example.testapp.Engine
import com.example.testapp.utils.MainCoroutineScopeRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class CarFeatureTests {

    private val engine = Engine(120, 200, 15, false)
    private val car = Car(5.0, engine)

    @get:Rule
    val mainCoroutineScopeRule = MainCoroutineScopeRule()

    @Test
    fun carLosesFuelWhenTurnedOn(){
        car.turnOn()
        assertEquals(4.5, car.fuel)
    }

    @Test
    fun carEngineGraduallyWarmsUpUponIgnition() = runTest {
        car.turnOn()

        advanceTimeBy(2001)
        assertEquals(45, car.engine.temp)

        advanceTimeBy(2000)
        assertEquals(70, car.engine.temp)

        advanceTimeBy(2000)
        assertEquals(95, car.engine.temp)

        assertTrue(car.engine.isTurnedOn)
    }
}
