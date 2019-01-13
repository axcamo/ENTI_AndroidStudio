package com.perea.marc.streampad

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.bumptech.glide.Glide
import com.perea.marc.streampad.model.TWStreamResponse
import com.perea.marc.streampad.model.TWUserResponse
import com.perea.marc.streampad.network.ApiService
import kotlinx.android.synthetic.main.activity_channel.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import android.net.Uri


class ChannelActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel)

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
                            Log.i("ChannelActivity", user.toString())
                            profile_username.text = user.displayName
                            Glide.with(applicationContext).load(user.getProfileThumbnail()).into(user_profile_pic)
                            total_views_number.text = user.viewCount.toString()
                            profile_bio.text = user.description

                            go_to_stream.setOnClickListener {
                                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.twitch.tv/"+user.login))
                                startActivity(browserIntent)
                            }
                        }
                    } ?: Log.e("ChannelActivity", "Error getting streams")
                } else {
                    // TODO: Handle error
                }
            }

        })

    }
}