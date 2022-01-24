package com.example.testapp.playlist

import com.example.testapp.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.mipmap.playlist
) {
}
