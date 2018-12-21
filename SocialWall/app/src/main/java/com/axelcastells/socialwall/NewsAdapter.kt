package com.axelcastells.socialwall

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.row_message.view.*

class NewsAdapter(var newsList: ArrayList<NewsModel>) : RecyclerView.Adapter<NewsAdapter.MessageNewsViewHolder>() {

    override fun getItemCount(): Int {
        return newsList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_notification, parent, false)
        return MessageNewsViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MessageNewsViewHolder, position: Int) {
        // viewHolder.imageMessage.imageAlpha = newsList[position].title
        viewHolder.usernameMessage.text = newsList[position].username
        viewHolder.messageSent.text = newsList[position].text
    }


    class MessageNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var imageMessage: ImageView = itemView.imatgeMissagte
        var usernameMessage: TextView = itemView.usernameMessage
        var messageSent: TextView = itemView.messageSent
    }

}