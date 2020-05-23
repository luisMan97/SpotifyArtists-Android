package com.example.spotifysongslist.APIManager

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("artists")
    fun getArtist(): Call<JsonObject>
}