package com.example.spotifysongslist.modules.songs.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifysongslist.R
import com.example.spotifysongslist.modules.songs.model.Artist
import com.example.spotifysongslist.modules.songs.router.ArtistsRouting
import com.example.spotifysongslist.modules.songs.view.adapter.ArtistsAdapter
import com.example.spotifysongslist.modules.songs.view.adapter.ArtistsListener
import kotlinx.android.synthetic.main.content_list.*

class FavoriteArtistsActivity : AppCompatActivity(), ArtistsListener {

    private lateinit var artistsAdapter: ArtistsAdapter
    private var routing = ArtistsRouting(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_artists)
        setupAdapter()
    }

    private fun setupAdapter() {
        artistsAdapter = ArtistsAdapter(this)
        rvSongs.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = artistsAdapter
        }
    }

    override fun onSongClicked(artist: Artist, position: Int) {
        routing.presentArtistSelection(artist)
    }

    override fun onFavoriteAddClicked(artist: Artist, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
