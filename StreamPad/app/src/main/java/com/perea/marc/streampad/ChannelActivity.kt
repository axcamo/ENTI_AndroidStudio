package com.perea.marc.streampad

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

class ChannelActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameId = intent.getStringExtra("gameId")

    }
}