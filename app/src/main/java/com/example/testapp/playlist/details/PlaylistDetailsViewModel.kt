package com.example.testapp.playlist.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class PlaylistDetailsViewModel(
    private val service: PlaylistDetailsService
) : ViewModel() {

    val playlistDetails: MutableLiveData<Result<PlaylistDetails>> = MutableLiveData()
    val loader = MutableLiveData<Boolean>()

    fun getPlaylistDetails(id: String) {
        viewModelScope.launch {
            loader.postValue(true)
            service.fetchPlaylistDetails(id)
                .onEach {
                    loader.postValue(false)
                }
                .collect {
                    playlistDetails.postValue(it)

                }
        }
    }
}
