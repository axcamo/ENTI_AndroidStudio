package com.axelcastells.socialwall

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.row_message.view.*

class HomeAdapter(var homeList: ArrayList<MessageModel>) : RecyclerView.Adapter<HomeAdapter.MessageHomeViewHolder>() {

    override fun getItemCount(): Int {
        return homeList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_message, parent, false)
        return MessageHomeViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MessageHomeViewHolder, position: Int) {
        // viewHolder.imageMessage.imageAlpha = newsList[position].title
        viewHolder.usernameMessage.text = homeList[position].username
        viewHolder.messageSent.text = homeList[position].text
    }


    class MessageHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var imageMessage: ImageView = itemView.imatgeMissagte
        var usernameMessage: TextView = itemView.usernameMessage
        var messageSent: TextView = itemView.messageSent
    }

}