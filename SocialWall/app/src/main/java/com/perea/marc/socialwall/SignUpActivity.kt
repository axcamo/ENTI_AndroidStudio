package com.perea.marc.socialwall

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.os.Bundle
import android.os.PatternMatcher
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        submitButton.setOnClickListener {
            //1. Get from data
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            //2. Validate from data
            if (!username.isEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                progressbar.visibility = View.VISIBLE
                submitButton.isEnabled = false

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SignUpActivity", "createUserWithEmail:success")
                            val userAuth = FirebaseAuth.getInstance().getCurrentUser()

                            userAuth.let { userAuth ->
                                // Create user profile
                                val userProfile =
                                    UserProfileModel(username = username, email = email, userId = userAuth.uid)
                                db.collection(USERS_COLLECTION).document(userAuth.uid.set(userProfile))
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccesful) {
                                            // Yey! Sign up completed
                                            finish()
                                        } else {
                                            // Error setting User Profile
                                            progressbar.visibility = View.GONE
                                            submitButton.isEnabled = true
                                            Toast.makeText(
                                                this@SignUpActivity, "User Profile creation failed.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            progressbar.visibility = View.GONE
                            submitButton.isEnabled = true
                            Log.w("SignUpActivity", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                this@SignUpActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                // TODO: Notify user
                Toast.makeText(
                    this@SignUpActivity, "Sign in successful.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        //TODO: Go to login
        goToLogin.setOnCLickListener {
            //TODO: Start login activity
            finish()
        }
    }
}
