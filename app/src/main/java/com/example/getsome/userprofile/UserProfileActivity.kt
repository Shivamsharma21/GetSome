package com.example.getsome.userprofile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.getsome.R
import com.example.getsome.dashboard.WelcomeScreen
import com.example.getsome.databinding.UserProfileActivityBinding
import com.example.getsome.model.UserModel

class UserProfileActivity :AppCompatActivity(),UserProfileInterface{

    private lateinit var userProfileViewModel: UserProfileViewModel
    private lateinit var viewBinding:UserProfileActivityBinding
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.user_profile_activity)
        supportActionBar?.hide()

       viewBinding.updateUserBtn.setOnClickListener {
           dataGetter()
           userProfileViewModel.insertinguserdetails()
           gotoDashboard()
       }
    }
      override fun dataGetter(){
          var username = viewBinding.nameEdittext.text.toString()
          var useraddress = viewBinding.addressEdittext.text.toString()
          var userphone:Int= Integer.parseInt(viewBinding.phoneEdittext.text.toString())
          userProfileViewModel = ViewModelProvider(this,UserProfileViewModelFactory(UserModel(username,useraddress,userphone))).get(UserProfileViewModel::class.java)
          lifecycle.addObserver(userProfileViewModel) //adding the lifecycle observer to the viewmodel //
      }

    override fun gotoDashboard() {
       val intent = Intent(this,WelcomeScreen::class.java)
        startActivity(intent)
    }


}
