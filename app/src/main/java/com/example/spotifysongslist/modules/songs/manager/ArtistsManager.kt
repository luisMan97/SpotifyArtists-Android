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
                t.stackTrace
                callback(null, t.message)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                response.body()?.let {
                    val offersJsonArray = it.getAsJsonArray("artists")
                    val songs = ArrayList<Artist>()

                    offersJsonArray?.let { array ->
                        array.forEach { jsonElement: JsonElement ->
                            val jsonObject = jsonElement.asJsonObject
                            val song = Artist(jsonObject)
                            songs.add(song)
                        }

                        callback(songs, null)
                    } ?: run {
                        callback(null, "Error get artist")
                    }
                } ?: run {
                    callback(null, "Error get body")
                }
            }
        })
    }

}