package com.example.spotifysongslist.modules.songs.view.adapter

import com.example.spotifysongslist.modules.songs.model.Artist

interface ArtistsListener {
    fun onSongClicked(artist: Artist, position: Int)
    fun onFavoriteAddClicked(artist: Artist, position: Int)
}