package com.example.getsome.userprofile

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.example.getsome.model.UserModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserProfileViewModel(val userModel: UserModel?) :ViewModel(),LifecycleObserver {

    constructor() : this(null)

    lateinit var firebasedatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Log.d("ViewModel ->","OnCreate")
        Log.d("UserData - >",userModel?.username.toString())
        firebasedatabase = FirebaseDatabase.getInstance()
        databaseReference = firebasedatabase.getReference("Users")
    }
  //  this function inserting the user profile info to the DB //
    fun insertinguserdetails(){
   //   databaseReference.push().child("Name").child()
        databaseReference.push().setValue(userModel)
          .addOnSuccessListener {
               Log.d("User Saved","Data Saved")
          }.addOnFailureListener {
              Log.d("Saved Failed",it.message.toString())
          }
    }
}