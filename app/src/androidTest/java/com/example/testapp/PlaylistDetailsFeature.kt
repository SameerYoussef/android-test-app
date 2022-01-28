package com.example.testapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.testapp.utils.BaseUITest
import com.example.testapp.utils.ViewMatchersExtensions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.CoreMatchers
import org.junit.Test

class PlaylistDetailsFeature : BaseUITest() {

    @Test
    fun playlistNameAndDetailsDisplay() {
        val positionZero = ViewMatchers.isDescendantOfA(
            ViewMatchersExtensions.nthChildOf(
                ViewMatchers.withId(R.id.playlists_list),
                0
            )
        )

        Espresso.onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.playlist_name), positionZero))
            .perform(ViewActions.click())

        assertDisplayed("Hard Rock Cafe")
        assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")
    }
}
