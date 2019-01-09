package com.perea.marc.streampad

import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.gson.internal.Streams
import com.perea.marc.streampad.model.TWStreamResponse
import com.perea.marc.streampad.network.ApiService
import com.perea.marc.streampad.model.TWStream


import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //search_list.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        getApiData()
    }

    fun getApiData() {
        //Get Streams
        ApiService.service.getStreams().enqueue(object : Callback<TWStreamResponse> {
            override fun onFailure(call: Call<TWStreamResponse>, t: Throwable) {
                // No response from server
                // TODO: Handle error
                t.printStackTrace()
            }

            override fun onResponse(call: Call<TWStreamResponse>, response: Response<TWStreamResponse>) {
                // We got response from server
                if (response.isSuccessful){
                    response.body()?.data?.let { streams ->
                        for (stream in streams) {
                            Log.i("MainActivity", streams.toString())
                            Log.i("MainActivity", stream.streamURL)
                            Log.i("MainActivity", stream.getStreamThumbnail())

                            // Get Game
                            stream.gameId?.let {
                                ApiService.service.getGames(it).enqueue(object : Callback<Any> {
                                    override fun onFailure(call: Call<Any>, t: Throwable) {
                                        t.printStackTrace()
                                    }

                                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                                        Log.i("MainActivity", response.body()?.toString() ?: "")
                                    }
                                })
                            }
                        }
                    } ?: Log.e("MainActivity", "Error getting streams")
                }
                else {
                    // TODO: Handle error
                }
            }

        })
        var streams = ApiService.service.getStreams()

    }
    

}

