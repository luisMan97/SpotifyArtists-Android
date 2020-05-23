package com.example.spotifysongslist.modules.songs.manager

import android.util.Log
import com.example.spotifysongslist.APIManager.APIManager
import com.example.spotifysongslist.Utils.callbackResponse
import com.example.spotifysongslist.modules.songs.model.Artist
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistsManager {

    private val apiService = APIManager().getClientService()
    private val call = apiService.getArtist()

    fun getArtists(callback: callbackResponse<ArrayList<Artist>>) {
        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message ?: "")
                t.stackTrace
                callback(null, t)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("hits")
                val songs = ArrayList<Artist>()

                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    val jsonObject = jsonElement.asJsonObject
                    val song = Artist(jsonObject)
                    songs.add(song)
                }

                callback(songs, null)
            }
        })
    }

}