package com.perea.marc.streampad

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.perea.marc.streampad.model.TWStreamResponse
import com.perea.marc.streampad.model.TWUserResponse
import com.perea.marc.streampad.network.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChannelActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = intent.getStringExtra("userId")

        ApiService.service.getUsers(userId).enqueue(object : Callback<TWUserResponse> {
            override fun onFailure(call: Call<TWUserResponse>, t: Throwable) {
                // No response from server
                // TODO: Handle error
                t.printStackTrace()
            }

            override fun onResponse(call: Call<TWUserResponse>, response: Response<TWUserResponse>) {
                // We got response from server
                if (response.isSuccessful) {
                    response.body()?.data?.let { users ->
                        for(user in users)
                        {
                            // TODO: Fill XML variables with retrieved user data.
                        }
                    } ?: Log.e("ChannelActivity", "Error getting streams")
                } else {
                    // TODO: Handle error
                }
            }

        })

    }
}