package com.example.spotifysongslist.APIManager

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIManager {

    private val urlApi = "https://api.spotify.com/v1/"

    fun getClientService(): ApiService {
        val authInterceptor = Interceptor {
            val url = it.request().url().newBuilder()
                .addQueryParameter("format", "json")
                .build()

            val newRequest = it.request()
                .newBuilder()
                .url(url)
                .addHeader("Authorization", "Bearer BQBcVrTixPxedWBsXAigW6cKUlwLqdXxoUKObe-KpYqx5W-S4VcZKcbFiEhtPQJ0d_GNUDtN3MMURV0t6gwtNlyXRYJHjqGZCNSRmGFU7qbKlw__BPmPSq8OvbSpn1u92_f3woxAaQ4G4-8QRi_QcwBYJvEes4ZnO5HpUWnFfW02")
                .build()

            it.proceed(newRequest)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(urlApi)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

}