package com.example.getsome.login

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginViewModel :ViewModel() {
    lateinit var firebasedatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onStart(){
        firebasedatabase = FirebaseDatabase.getInstance()
        databaseReference = firebasedatabase.getReference("Users")
    }


    fun inserting_userDetails(){

    }

}

