package com.example.testapp.unittests.playlist

import com.example.testapp.R
import com.example.testapp.playlist.PlaylistMapper
import com.example.testapp.playlist.PlaylistRaw
import com.example.testapp.utils.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaylistMapperShould : BaseUnitTest() {

    private val playlistRaw = PlaylistRaw("1", "a name", "a category")
    private val mapper = PlaylistMapper()
    private val playlists = mapper(listOf(playlistRaw))
    private val playlist = playlists[0]

    @Test
    fun keepSameIdNameAndCategory() {
        assertEquals(playlistRaw.id, playlist.id)
        assertEquals(playlistRaw.name, playlist.name)
        assertEquals(playlistRaw.category, playlist.category)
    }

    @Test
    fun mapDefaultImageWhenNotRock() {
        assertEquals(R.mipmap.playlist, playlist.image)
    }

    @Test
    fun mapRockImageWhenRock() {
        val rockPlaylist =
            mapper.invoke(listOf(PlaylistRaw("1", "a name", "rock")))[0]

        assertEquals(R.mipmap.rock, rockPlaylist.image)
    }
}
