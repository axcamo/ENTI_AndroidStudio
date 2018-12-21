package com.axelcastells.socialwall

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        logInMessage.setOnClickListener {
            val goToLoginIntent = Intent(this, LogInActivity::class.java)
            startActivity(goToLoginIntent)
            return@setOnClickListener
        }

        signUpButton.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(signUpEmail.text.toString(), signUpPassword.text.toString())
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        Log.d("SignUpActivity", "createUserWithEmail:success")
                        val user = auth.currentUser
                        val userModel = UserModel(user?.uid, user?.displayName, user?.email)

                        val db = FirebaseFirestore.getInstance()
                        db.collection("users").document(user!!.uid).set(userModel)
                            .addOnSuccessListener{
                            finish()
                        }.addOnFailureListener {

                            Log.e("SignUpActivity", it.message)
                        }
                    }else{
                        Log.w("SignUpActivity", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this@SignUpActivity, "Authentication failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
