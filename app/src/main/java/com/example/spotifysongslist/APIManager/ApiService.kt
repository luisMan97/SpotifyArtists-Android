package com.example.spotifysongslist.APIManager

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("search_by_date?query=android")
    fun getArtist(): Call<JsonObject>
}