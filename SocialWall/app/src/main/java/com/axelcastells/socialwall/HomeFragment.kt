package com.axelcastells.socialwall


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Your code goes here
        //refreshData()
        SendMessageButton.setOnClickListener {
            val userText = TextCapsule.text.toString()
            if (userText.isEmpty()) return@setOnClickListener

            //if user == null -> Sign Up
            if (FirebaseAuth.getInstance().currentuser == null){
                val goToSignUpIntent = Intent(activity, SignUpActivity::class.java)
                startActivity(goToSignUpIntent)
                return@setOnClickListener
            }
            Log.i("HomeFragment", "Text Message: "+userText)
            SendMessage(userText)
        }

        GetMessages()
    }

    //send message to database
    fun SendMessage(msg:String?){
        val db = FirebaseFirestore.getInstance()
        val userMessage = MessageModel(msg, Date())
        db.collection("messages").add(userMessage).addOnSuccessListener {
            GetMessages()
        }.addOnFailureListener{
            Log.i("HomeFragment", "Error! Retry sending message!")
        }
    }

    //get messages from database
    fun GetMessages(){
        val db = FirebaseFirestore.getInstance()
        db.collection("messages").get().addOnCompleteListener{task ->
            if(task.isSuccessful){
                var list = ArrayList<MessageModel>()

                task.result?.forEach{documentSnapshot->
                    val message = documentSnapshot.toObject(MessageModel::class.java)
                    list.add(message)
                    Log.i("HomeFragment", "Got message from FireStore: "+message)
                }
                var adapter = NewsAdapter(list)
                messageslist.adapter = adapter
                messageslist.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}
