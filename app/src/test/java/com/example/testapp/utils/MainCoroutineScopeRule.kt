package com.example.testapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Adapted from:
 * https://stackoverflow.com/questions/58303961/kotlin-coroutine-unit-test-fails-with-module-with-the-main-dispatcher-had-faile
 */

class MainCoroutineScopeRule : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    }
}
