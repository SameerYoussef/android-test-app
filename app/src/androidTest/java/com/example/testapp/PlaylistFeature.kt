package com.example.testapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.testapp.utils.OkHttpIdlingResourceRule
import com.example.testapp.utils.ViewMatchersExtensions
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

    @get:Rule
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val okHttpIdlingResourceRule = OkHttpIdlingResourceRule()

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }

    @Test
    fun displayListOfPlaylists() {

        assertRecyclerViewItemCount(R.id.playlists_list, 10)

        val positionZero = isDescendantOfA(ViewMatchersExtensions.nthChildOf(withId(R.id.playlists_list), 0))

        onView(allOf(withId(R.id.playlist_name), positionZero))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_category), positionZero))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_image), positionZero))
            .check(matches(allOf(withContentDescription("playlist_image"), withDrawable(R.mipmap.playlist))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displaysLoaderWhileFetchingPlaylists() {
        assertDisplayed(R.id.loader)
    }
}
