package com.perea.marc.streampad.network


import com.perea.marc.streampad.model.TWGameResponse
import com.perea.marc.streampad.model.TWStreamResponse
import com.perea.marc.streampad.model.TWStream
import com.perea.marc.streampad.model.TWUserResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Client-ID: ywvglt0gib8rqdly0ejobehqfi071m")
    @GET("streams")
    fun getStreams(@Query("game_id") gameId: String): Call<TWStreamResponse>


    @Headers("Client-ID: ywvglt0gib8rqdly0ejobehqfi071m")
    @GET("games")
    fun getGames(@Query("name") gameName: String): Call<TWGameResponse>

    @Headers("Client-ID: ywvglt0gib8rqdly0ejobehqfi071m")
    @GET("users")
    fun getUsers(@Query("id") id: String): Call<TWUserResponse>

    companion object {
        private var retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/helix/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<ApiService>(ApiService::class.java)
    }

}