package com.example.testapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

    @get:Rule
    val mActivityRule = ActivityTestRule(MainActivity::class.java)
    
    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }
}
