package com.perea.marc.twitchcompanion.network

import com.perea.marc.twitchcompanion.Model.TWStream
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Client-ID: ywvglt0gib8rqdly0ejobehqfi071m")
    @GET("streams")
    fun getStreams(): ArrayList<TWStream>

    companion object {
        private var retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/helix/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<ApiService>(ApiService::class.java)
    }

}