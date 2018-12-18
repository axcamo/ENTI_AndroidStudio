package com.perea.marc.socialwall


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshData()
        sendButton.setOnClickListener {
            val messageText = inputText.text.toString()
            if (messageText.isEmpty()) return@setOnClickListener


            // If user == null -> Sign in
            if (FirebaseAuth.getInstance().currentUser == null) {
                val goToSignUpIntent = Intent(activity, SignUpActivity::class.java)
                startActivity(goToSignUpIntent)
                return@setOnClickListener
            }

            // Get user
            FirebaseAuth.getInstance().currentUser?.uid.let { userId ->
                db.collection(USERS_COLLECTION).document(userId).get()
                    .addOnCompleteListener {
                        if (task.isSuccessful) {
                            val userProfile = task.result?.toObject(UserProfileModel)::class.java
                            userProfile.let { userProfile ->
                                // Send message to database
                                val message = MessageModel(
                                    text = messageText,
                                    createAt = Date(),
                                    username = userProfile.username,
                                    userId = userProfile.userId
                                )
                                val db = FireBaseFireStore.getInstance()
                                db.collection(MESSAGES_COLLECTION)
                                    .add(message)
                                    .addOnSuccessListener { documentReference ->
                                        Toast.makeText(activity, R.string.add_message_success, Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                    .addOnFailureListener { e ->
                                        e.printStackTrace()
                                        Toast.makeText(activity, R.string.add_message_error, Toast.LENGTH_SHORT).show()
                                    }
                            }
                        } else {
                            // TODO: Handle error
                            Toast.makeText(activity, R.string.getting_user_error, Toast.LENGTH_SHORT).show()
                            FirebaseAuth.getInstance().signOut()
                        }
                    }


            }


            fun refreshData() {
                val db = FirebaseFirestore.getInstance()
                db.collection("users")
                    .document(user.uid)
                    .set(userModel)
                    .addOnSuccessListener {
                        // Sign Up Completed!
                        // TODO: Tell user everything was fine and finish!

                    }.addOnFailureListener {
                        // TODO: Handle failure
                        Log.e("SignUpActivity", it.message)
                    }
            }
        }
    }
}
