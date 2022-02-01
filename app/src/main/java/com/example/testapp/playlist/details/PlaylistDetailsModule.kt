package com.example.testapp.playlist.details

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
class PlaylistDetailsModule {
    @Provides
    fun playlistDetailsApi(retrofit: Retrofit) : PlaylistDetailsApi = retrofit.create(PlaylistDetailsApi::class.java)
}
