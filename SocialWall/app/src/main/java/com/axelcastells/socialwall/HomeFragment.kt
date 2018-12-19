package com.axelcastells.socialwall


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
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
        SendMessageButton.setOnClickListener {
            val userText = TextCapsule.text.toString()
            Log.i("HomeFragment", "Text Message: "+userText)
            SendMessage(userText)
        }

        GetMessages()
    }

    fun SendMessage(msg:String?){
        val db = FirebaseFirestore.getInstance()
        val userMessage = MessageModel(msg, Date())
        db.collection("messages").add(userMessage).addOnSuccessListener {
            GetMessages()
        }.addOnFailureListener{
            Log.i("HomeFragment", "Error! Retry sending message!")
        }
    }

    fun GetMessages(){
        val db = FirebaseFirestore.getInstance()
        db.collection("messages").get().addOnCompleteListener{task ->
            if(task.isSuccessful){
                task.result?.forEach{documentSnapshot->
                    val message = documentSnapshot.toObject(MessageModel::class.java)
                    Log.i("HomeFragment", "Got message from FireStore: "+message)
                }
            }
        }
    }
}
