package com.example.testapp.unittests.car

import com.example.testapp.Car
import com.example.testapp.Engine
import com.example.testapp.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CarShould : BaseUnitTest() {

    private val engine: Engine = mock()
    private val car: Car

    init {
        runTest {
            whenever(engine.turnOn()).thenReturn(
                flow {
                    delay(2000)
                    emit(45)
                    delay(2000)
                    emit(70)
                    delay(2000)
                    emit(95)
                }
            )
        }
        car = Car(5.0, engine)
    }

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
