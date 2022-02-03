package com.example.testapp.utils

import androidx.test.espresso.IdlingRegistry
import com.example.testapp.playlist.okHttpClient
import com.jakewharton.espresso.OkHttp3IdlingResource
import org.junit.rules.ExternalResource

class OkHttpIdlingResourceRule : ExternalResource() {

    override fun before() {
        registerIdlingResource()
    }

    private fun registerIdlingResource() {
        okHttpClientIdlingResource =
            OkHttp3IdlingResource.create("OkHttp", okHttpClient).also {
                IdlingRegistry.getInstance().register(it)
            }
    }

    override fun after() {
        unregisterIdlingResource()
    }

    companion object {

        private lateinit var okHttpClientIdlingResource: OkHttp3IdlingResource

        fun unregisterIdlingResource() {
            IdlingRegistry.getInstance().unregister(okHttpClientIdlingResource)
        }
    }
}
