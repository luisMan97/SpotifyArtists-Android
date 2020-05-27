package com.example.spotifysongslist.modules.songs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spotifysongslist.modules.songs.manager.ArtistsManager
import com.example.spotifysongslist.modules.songs.model.Artist

class ArtistsViewModel: ViewModel() {

    private val manager = ArtistsManager()
    private var listArtist: MutableLiveData<List<Artist>> = MutableLiveData()
    private var isLoading = MutableLiveData<Boolean>()
    private var errorMessage = MutableLiveData<String>()

    fun getListArtist(): LiveData<List<Artist>> = listArtist
    fun getIsLoading(): LiveData<Boolean> = isLoading
    fun getErrorMessage(): LiveData<String> = errorMessage

    fun getArtists() {
        manager.getArtists { artists, error ->
            error?.let {
                processFinished()
                errorMessage.postValue(it)
            }

            artists?.let {
                processFinished()
                listArtist.postValue(it)
            }
        }
    }

    private fun processFinished() {
        isLoading.postValue(true)
    }

}