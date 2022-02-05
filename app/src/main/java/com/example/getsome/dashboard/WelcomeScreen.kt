package com.example.getsome.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.getsome.R
import com.example.getsome.userprofile.UserProfileActivity
import com.google.firebase.auth.FirebaseAuth

class WelcomeScreen: AppCompatActivity() {

    var state =1
    lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

          var intent = getIntent()
        state = intent.getIntExtra("UserState",1)
        Log.d("Intent State",state.toString())
        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        if(state ==0){
            val intent = Intent(this,UserProfileActivity::class.java)
            startActivity(intent)
            Log.d("Going to UserProfile","OnStart")
        }
    }
}