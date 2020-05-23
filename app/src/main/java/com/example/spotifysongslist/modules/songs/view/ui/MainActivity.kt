package com.example.spotifysongslist.modules.songs.view.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifysongslist.R
import com.example.spotifysongslist.modules.songs.model.Artist
import com.example.spotifysongslist.modules.songs.router.ArtistsRouting
import com.example.spotifysongslist.modules.songs.view.adapter.ArtistsAdapter
import com.example.spotifysongslist.modules.songs.view.adapter.ArtistsListener
import com.example.spotifysongslist.modules.songs.viewmodel.ArtistsViewModel
import kotlinx.android.synthetic.main.content_list.*

class MainActivity : AppCompatActivity(), ArtistsListener {

    private lateinit var artistsAdapter: ArtistsAdapter
    private lateinit var viewModel: ArtistsViewModel
    private var routing = ArtistsRouting(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdapter()
        setupViewModel()
    }

    private fun setupAdapter() {
        artistsAdapter = ArtistsAdapter(this)
        rvSongs.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = artistsAdapter
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(ArtistsViewModel::class.java)
        viewModel.getArtists()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getListArtist().observe(this, Observer<List<Artist>> {
            artistsAdapter.updateData(it)
        })

        viewModel.getIsLoading().observe(this, Observer<Boolean> {
            if (it != null)
                rlBaseSongs.visibility = View.INVISIBLE
        })

        viewModel.getErrorMessage().observe(this, Observer<String> {
            Log.e("MainActivity", it)
        })
    }

    // SongsAdapter Methods

    override fun onSongClicked(artist: Artist, position: Int) {
        routing.presentArtistSelection(artist)
    }

    override fun onFavoritelicked(artist: Artist, position: Int) {
        routing.presentFavoriteArtists(artist)
    }
}
