package com.example.testapp.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val okHttpClient = OkHttpClient()

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun playlistApi(retrofit: Retrofit) : PlaylistApi = retrofit.create(PlaylistApi::class.java)

    @Provides
    fun retrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.103:3000/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
