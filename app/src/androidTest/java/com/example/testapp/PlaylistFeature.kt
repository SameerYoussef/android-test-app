package com.example.testapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testapp.utils.BaseUITest
import com.example.testapp.utils.ViewMatchersExtensions
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistFeature : BaseUITest() {

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }

    @Test
    fun displayListOfPlaylists() {

        assertRecyclerViewItemCount(R.id.playlists_list, 10)

        val positionZero = isDescendantOfA(ViewMatchersExtensions.nthChildOf(withId(R.id.playlists_list), 1))

        onView(allOf(withId(R.id.playlist_name), positionZero))
            .check(matches(withText("Chilled House")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_category), positionZero))
            .check(matches(withText("house")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_image), positionZero))
            .check(matches(allOf(withContentDescription("playlist_image"), withDrawable(R.mipmap.playlist))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displaysLoaderWhileFetchingPlaylists() {
        okHttpIdlingResourceRule.unregisterIdlingResource()
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoader() {
        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displaysRockImageForRockItems() {
        onView(allOf(
            withId(R.id.playlist_image),
            isDescendantOfA(ViewMatchersExtensions.nthChildOf(withId(R.id.playlists_list), 0))
        ))
            .check(matches(allOf(withContentDescription("playlist_image"), withDrawable(R.mipmap.rock))))
            .check(matches(isDisplayed()))

        onView(allOf(
            withId(R.id.playlist_image),
            isDescendantOfA(ViewMatchersExtensions.nthChildOf(withId(R.id.playlists_list), 3))
        ))
            .check(matches(allOf(withContentDescription("playlist_image"), withDrawable(R.mipmap.rock))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToDetailsScreen() {
        val positionZero = isDescendantOfA(ViewMatchersExtensions.nthChildOf(withId(R.id.playlists_list), 0))

        onView(allOf(withId(R.id.playlist_name), positionZero))
            .perform(click())

        assertDisplayed(R.id.playlists_details_root)
    }
}
