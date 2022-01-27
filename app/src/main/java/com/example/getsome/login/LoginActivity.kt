package com.example.getsome

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.getsome.dashboard.WelcomeScreen
import com.example.getsome.databinding.ActivityMainBinding
import com.example.getsome.login.RegisterUserBottomSheet
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(),RegisterUserBottomSheet.BottomsheetListner {
    var bottomSheetDialogFragment = RegisterUserBottomSheet()
    lateinit var firebaseAuth: FirebaseAuth
    var state:Int = 1
    lateinit var viewBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

           supportActionBar?.hide()
           loginbackground()

        viewBinding.googlesignInButton.setOnClickListener {
            Log.d("BUTTONCLICKED","clicked")
            loginUser()
        }

        viewBinding.newuser.setOnClickListener {
            bottomSheetDialogFragment.show(supportFragmentManager,"Reg")
        }

    }

    fun loginbackground() {
        var videopath: String = "android.resource://" + packageName + "/" + R.raw.shoppingnew;
        var videopath2: String = "android.resource://" + packageName + "/" + R.raw.vd31

        var uri = Uri.parse(videopath)
        viewBinding.myvideo.setVideoURI(uri)

        viewBinding.myvideo.setOnPreparedListener {
            if (!it.isPlaying) {
                viewBinding.myvideo.start()
            }
        }
        viewBinding.myvideo.start()

        viewBinding.myvideo.setOnCompletionListener(MediaPlayer.OnCompletionListener {
            if (state == 1) {
                it.reset() // second vid
                viewBinding.myvideo.setVideoPath(videopath2)
                viewBinding.myvideo.start()
                state = 0
            } else if (state == 0) {
                it.reset()
                viewBinding.myvideo.setVideoPath(videopath)
                viewBinding.myvideo.start()
                state = 1
            }
        })
    }

    fun loginUser(){

        firebaseAuth.signInWithEmailAndPassword(viewBinding.emailUser.text.toString()
            ,viewBinding.passworduser.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    var intent = Intent(this,WelcomeScreen::class.java)
                    startActivity(intent)

                    Toast.makeText(this,"User SignIN",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                }
        }
    }
    override fun onclicklistnerBS(value: String) {
          if (value.equals("yes")){
              bottomSheetDialogFragment.dismiss()
              Log.d("GetBack","MainActivity")
          }
    }

}