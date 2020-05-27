package com.example.spotifysongslist.modules.songs.router

import android.app.Activity
import android.content.Intent
import com.example.spotifysongslist.R
import com.example.spotifysongslist.modules.songs.model.Artist
import com.example.spotifysongslist.modules.songs.view.ui.ArtistDetailActivity
import com.example.spotifysongslist.modules.songs.view.ui.FavoriteArtistsActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class ArtistsRouting(var view: Activity) {

    fun presentArtistSelection(artist: Artist) {
        val bottomSheetDialog = BottomSheetDialog(view)
        bottomSheetDialog.setContentView(R.layout.activity_artist_detail)
        bottomSheetDialog.setCanceledOnTouchOutside(false)
        bottomSheetDialog.show()

        //val intent = Intent(view, ArtistDetailActivity::class.java)
        //intent.putExtra("SONG", artist)

        //view.startActivity(intent)
    }

    fun presentFavoriteArtists(artist: Artist) {
        val intent = Intent(view, FavoriteArtistsActivity::class.java)
        intent.putExtra("SONG", artist)
        view.startActivity(intent)
    }

}