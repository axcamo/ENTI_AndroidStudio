package com.axelcastells.socialwall


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    fun UpdateMessages(){
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
