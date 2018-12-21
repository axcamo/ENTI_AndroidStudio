package com.axelcastells.socialwall


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.axelcastells.socialwall.R.string.username
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Check if logged in or out
        FirebaseAuth.getInstance().currentUser?.let {
            // We have user
            // Toggle Fields Visibility
            userFields.visibility = View.VISIBLE
            signUpButton.visibility = View.GONE


            // Fill user data
            val db = FirebaseFirestore.getInstance()
            // Get user
            db.collection("users").document(it.uid).get()
                .addOnSuccessListener { documentSnapshot ->
                    val userProfile = documentSnapshot.toObject(UserProfile::class.java)
                    Log.i("ProfileFragment", "Got user profile: " + userProfile)
                    userProfile?.let { userProfile ->
                        // Populate fields
                        // TODO: Set Image userProfile.avatarUrl
                        username.text = userProfile.username
                        profileEmail.text = userProfile.email
                        // Image change
                        profileImage.setOnClickListener {

                        }

                    }
                }
                .addOnFailureListener {
                    // TODO: failure getitng user
                }


        } ?: kotlin.run {
            // No user
            userFields.visibility = View.GONE
            signUpButton.visibility = View.VISIBLE
            signUpButton.setOnClickListener {
                val goToSignUpIntent = Intent(activity, SignUpActivity::class.java)
                startActivity(goToSignUpIntent)
                return@setOnClickListener
            }
        }
    }
}



