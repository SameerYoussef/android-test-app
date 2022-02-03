package com.example.testapp.unittests.details

import com.example.testapp.playlist.details.PlaylistDetails
import com.example.testapp.playlist.details.PlaylistDetailsService
import com.example.testapp.playlist.details.PlaylistDetailsViewModel
import com.example.testapp.utils.BaseUnitTest
import com.example.testapp.utils.captureValues
import com.example.testapp.utils.getValueForTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlaylistDetailsViewModelShould : BaseUnitTest() {

    private val service: PlaylistDetailsService = mock()
    private val playlistDetails: PlaylistDetails = mock()
    private val serviceSuccess = Result.success(playlistDetails)
    private val id = "1"

    @Test
    fun getPlaylistDetailsFromService() = runTest {
        val viewModel = PlaylistDetailsViewModel(service)
        viewModel.getPlaylistDetails(id)
        viewModel.playlistDetails.getValueForTest()
        verify(service, times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun emitPlaylistsFromService() = runTest {
        val viewModel = mockServiceSuccess()
        viewModel.getPlaylistDetails("1")

        assertEquals(serviceSuccess, viewModel.playlistDetails.getValueForTest())
    }

    @Test
    fun emitErrorWhenServiceFails()  = runTest {
        val exception = RuntimeException("something went wrong")
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )
        val viewModel = PlaylistDetailsViewModel(service)
        viewModel.getPlaylistDetails("1")

        assertEquals(exception, viewModel.playlistDetails.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun displayLoaderWhileFetching() = runTest {
        val viewModel = mockServiceSuccess()

        viewModel.loader.captureValues {
            viewModel.getPlaylistDetails(id)
            viewModel.playlistDetails.getValueForTest()
            assertEquals(true, values[0])
        }
    }

    @Test
    fun hideLoaderUponFetchComplete() = runTest {
        val viewModel = mockServiceSuccess()
        viewModel.getPlaylistDetails(id)
        viewModel.loader.captureValues {
            viewModel.playlistDetails.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    private fun mockServiceSuccess() : PlaylistDetailsViewModel {
        runTest {
            whenever(service.fetchPlaylistDetails(id)).thenReturn(
                flow {
                    emit(serviceSuccess)
                }
            )
        }
        return PlaylistDetailsViewModel(service)
    }
}
