package com.example.testapp.unittests.details

import com.example.testapp.playlist.details.PlaylistDetails
import com.example.testapp.playlist.details.PlaylistDetailsApi
import com.example.testapp.playlist.details.PlaylistDetailsService
import com.example.testapp.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever

class PlaylistDetailsServiceShould : BaseUnitTest() {

    private val api: PlaylistDetailsApi = mock()
    private var service = PlaylistDetailsService(api)
    private val id: String = "1"
    private val playlistDetails : PlaylistDetails = mock()

    @Test
    fun fetchPlaylistDetailsFromApi() = runTest {
        service.fetchPlaylistDetails(id).single()
        verify(api, times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runTest {
        whenever(api.fetchPlaylistDetails(id)).thenReturn(playlistDetails)
        assertEquals(Result.success(playlistDetails), service.fetchPlaylistDetails(id).first())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runTest {
        whenever(api.fetchPlaylistDetails(id)).thenThrow()
        assertEquals(
            RuntimeException("something went wrong").message,
            service.fetchPlaylistDetails(id).first().exceptionOrNull()?.message
        )
    }
}
