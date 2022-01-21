package com.example.testapp.unittests

import com.example.testapp.Car
import com.example.testapp.Engine
import com.example.testapp.utils.MainCoroutineScopeRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class CarShould {

    private val engine: Engine = mock()
    private val car = Car(5.0, engine)

    @get:Rule
    val blockingRule = MainCoroutineScopeRule()

    @Test
    fun loseFuelWhenTurnedOn() {
        car.turnOn()
        assertEquals(4.5, car.fuel)
    }

    @Test
    fun turnOnItsEngine() = runTest {
        car.turnOn()

        verify(car.engine, times(1)).turnOn()
        verify(engine, times(1)).turnOn()

    }
}
