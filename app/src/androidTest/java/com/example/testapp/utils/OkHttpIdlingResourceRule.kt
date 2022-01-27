package com.example.testapp.utils

import androidx.test.espresso.IdlingRegistry
import com.example.testapp.playlist.PlaylistFragment
import com.jakewharton.espresso.OkHttp3IdlingResource
import org.junit.rules.ExternalResource

class OkHttpIdlingResourceRule : ExternalResource() {

    private lateinit var okHttpClientIdlingResource: OkHttp3IdlingResource

    override fun before() {
        okHttpClientIdlingResource =
            OkHttp3IdlingResource.create("OkHttp", PlaylistFragment.okHttpClient).also {
                IdlingRegistry.getInstance().register(it)
            }
    }

    override fun after() {
        unregisterIdlingResource()
    }

    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(okHttpClientIdlingResource)
    }
}
