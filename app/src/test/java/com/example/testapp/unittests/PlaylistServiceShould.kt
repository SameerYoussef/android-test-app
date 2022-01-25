package com.example.testapp.unittests

import com.example.testapp.playlist.Playlist
import com.example.testapp.playlist.PlaylistApi
import com.example.testapp.playlist.PlaylistService
import com.example.testapp.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val playlistApi: PlaylistApi = mock()
    private val playlistValues: List<Playlist> = mock()
    private val exception = RuntimeException("something went wrong")

    @Test
    fun fetchAllPlaylistsFromApi() = runTest {
        service = PlaylistService(playlistApi)
        service.fetchPlaylists().first()
        verify(playlistApi, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runTest {
        whenever(playlistApi.fetchAllPlaylists()).thenReturn(playlistValues)
        service = PlaylistService(playlistApi)
        assertEquals(Result.success(playlistValues), service.fetchPlaylists().first())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runTest {
        whenever(playlistApi.fetchAllPlaylists()).thenThrow(exception)
        service =  PlaylistService(playlistApi)
        assertEquals(exception.message,
            service.fetchPlaylists().first().exceptionOrNull()?.message)
    }
}
