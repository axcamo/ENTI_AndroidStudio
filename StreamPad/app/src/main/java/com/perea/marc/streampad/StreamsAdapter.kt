package com.perea.marc.streampad

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.perea.marc.streampad.model.TWStream
import com.perea.marc.streampad.model.TWStreamResponse
import com.perea.marc.streampad.network.ApiService
import kotlinx.android.synthetic.main.activity_channel.view.*
import kotlinx.android.synthetic.main.stream_row_layout.view.*

class StreamsAdapter(var streamsList: ArrayList<TWStream>) : RecyclerView.Adapter<StreamsAdapter.StreamViewHolder>() {


    override fun getItemCount(): Int {
        return streamsList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stream_row_layout, parent, false)
        return StreamViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: StreamViewHolder, position: Int) {
        viewHolder.streamTitle.text = streamsList[position].title
        viewHolder.channelName.text = streamsList[position].userName
        viewHolder.views.text = streamsList[position].viewerCount.toString()

        Glide.with(viewHolder.thumb.context).load(streamsList[position].getStreamThumbnail()).into(viewHolder.thumb)

        // PlayButton Event
        viewHolder.playButton.setOnClickListener {
            val stream = streamsList[position]


            val intent = Intent(it.context, ChannelActivity::class.java)
            intent.putExtra("userId", stream.userId)
            it.context.startActivity(intent)
        }
    }
    class StreamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var playButton: Button = itemView.play_button
        var streamTitle: TextView = itemView.stream_name
        var channelName: TextView = itemView.channel_name
        var views: TextView = itemView.viewers_count
        var thumb: ImageView = itemView.channel_thumbnail
    }
}