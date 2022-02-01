package com.example.testapp.unittests.playlist

import com.example.testapp.playlist.*
import com.example.testapp.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlaylistRepositoryShould: BaseUnitTest() {

    private val service: PlaylistService = mock()
    private val mapper: PlaylistMapper = mock()
    private val playlists = mock<List<Playlist>>()
    private val playlistsRaw = mock<List<PlaylistRaw>>()
    private val exception = RuntimeException("something went wrong")

    @Test
    fun getPlaylistFromService() = runTest {
        val repository = mockServiceSuccess()
        repository.getPlaylists()
        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun emitMappedPlaylistFromService() = runTest {
        val repository = mockServiceSuccess()
        assertEquals(playlists, repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runTest {
        val repository = mockServiceFailure()
        assertEquals(exception, repository.getPlaylists().first().exceptionOrNull())
    }

    @Test
    fun delegateBusinessLocalToMapper() = runTest {
        val repository = mockServiceSuccess()
        repository.getPlaylists().first()
        verify(mapper, times(1)).invoke(playlistsRaw)
    }

    private suspend fun mockServiceFailure(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.failure<List<PlaylistRaw>>(exception))
            }
        )
        return PlaylistRepository(service, mapper)
    }

    private suspend fun mockServiceSuccess(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.success(playlistsRaw))
            }
        )
        whenever(mapper.invoke(playlistsRaw)).thenReturn(
            playlists
        )
        return PlaylistRepository(service, mapper)
    }
}
