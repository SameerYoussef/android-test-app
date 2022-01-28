package com.example.testapp.utils

import androidx.test.rule.ActivityTestRule
import com.example.testapp.MainActivity
import org.junit.Rule

open class BaseUITest {

    @get:Rule
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val okHttpIdlingResourceRule = OkHttpIdlingResourceRule()
}
