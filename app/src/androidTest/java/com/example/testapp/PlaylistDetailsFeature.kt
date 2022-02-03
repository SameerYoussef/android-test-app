package com.example.testapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.testapp.utils.BaseUITest
import com.example.testapp.utils.OkHttpIdlingResourceRule
import com.example.testapp.utils.ViewMatchersExtensions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import org.hamcrest.CoreMatchers
import org.junit.Test

class PlaylistDetailsFeature : BaseUITest() {

    @Test
    fun playlistNameAndDetailsDisplayAfterLoadingSpinner() {
        navigateToNthPlaylist(0)

        assertDisplayed("Hard Rock Cafe")
        assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")
        assertNotExist(R.id.loader)
    }

    @Test
    fun loaderDisplaysWhileLoadingPlaylistDetails() {
        // Required to ensure Playlist screen completely loaded
        assertDisplayed("Hard Rock Cafe")

        // Unregister idling resource so assert can check spinner
        OkHttpIdlingResourceRule.unregisterIdlingResource()

        navigateToNthPlaylist(0) // click
        assertDisplayed(R.id.details_loader)
    }

    @Test
    fun loaderDisappearsOnceLoadingPlaylistDetailsComplete() {
        navigateToNthPlaylist(0)
        assertNotDisplayed(R.id.details_loader)
    }

    @Test
    fun errorTextAndSnackBarDisplaysUponFailedPlaylistFetch() {
        navigateToNthPlaylist(1) // click
        assertDisplayed("Sorry!")
        assertDisplayed("Playlist not found")
        assertDisplayed(R.string.generic_error)
    }

    @Test
    fun errorSnackBarDisplaysUponFailedPlaylistDetailsFetch() {
        // Required to ensure Playlist screen completely loaded
        assertDisplayed("Hard Rock Cafe")

        // Unregister idling resource so assert can check spinner
        OkHttpIdlingResourceRule.unregisterIdlingResource()

        navigateToNthPlaylist(1) // click
        assertDisplayed(R.string.generic_error)
    }

    private fun navigateToNthPlaylist(index: Int) {
        val recyclerViewItem = ViewMatchers.isDescendantOfA(
            ViewMatchersExtensions.nthChildOf(
                ViewMatchers.withId(R.id.playlists_list),
                index
            )
        )

        Espresso.onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.playlist_name), recyclerViewItem))
            .perform(ViewActions.click())
    }
}
