package com.example.testapp.unittests

import com.example.testapp.playlist.Playlist
import com.example.testapp.playlist.PlaylistRepository
import com.example.testapp.playlist.PlaylistViewModel
import com.example.testapp.utils.BaseUnitTest
import com.example.testapp.utils.captureValues
import com.example.testapp.utils.getValueForTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlaylistViewModelShould : BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("something went wrong")

    @Test
    fun getPlaylistsFromRepository() = runTest {

        val viewModel = mockRepositorySuccess()
        viewModel.playlists.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runTest {
        val viewModel = mockRepositorySuccess()
        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError()  = runTest {
        val viewModel = mockRepositoryFailure()
        assertEquals(exception, viewModel.playlists.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoading() = runTest {
        val viewModel = mockRepositorySuccess()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(true, values[0])
        }
    }

    @Test
    fun closeLoaderOncePlaylistLoads() = runTest {
        val viewModel = mockRepositorySuccess()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    @Test
    fun closeLoaderOncePlaylistOnError() = runTest {
        val viewModel = mockRepositoryFailure()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    private fun mockRepositorySuccess() : PlaylistViewModel {
        runTest {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return PlaylistViewModel(repository)
    }

    private suspend fun mockRepositoryFailure(): PlaylistViewModel {
        whenever(repository.getPlaylists()).thenReturn(
            flow {
                emit(Result.failure<List<Playlist>>(exception))
            }
        )
        return PlaylistViewModel(repository)
    }
}
