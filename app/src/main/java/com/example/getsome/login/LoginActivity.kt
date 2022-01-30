package com.example.getsome

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.getsome.dashboard.WelcomeScreen
import com.example.getsome.databinding.ActivityMainBinding
import com.example.getsome.login.RegisterUserBottomSheet
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class LoginActivity : AppCompatActivity(),RegisterUserBottomSheet.BottomsheetListner {

    lateinit var activity: Activity
    var bottomSheetDialogFragment = RegisterUserBottomSheet()
    lateinit var firebaseAuth: FirebaseAuth
    var state: Int = 1
    lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity = this

        var timer = Timer()
        firebaseAuth = FirebaseAuth.getInstance()

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportActionBar?.hide()
        loginbackground()

        viewBinding.googlesignInButton.setOnClickListener {
            Log.d("BUTTONCLICKED", "clicked")
            viewBinding.progressBar.visibility = VISIBLE
            loginUser()

        }

        viewBinding.newuser.setOnClickListener {
            bottomSheetDialogFragment.show(supportFragmentManager, "Reg")
        }


    }

    fun loginbackground() {
        var videopath: String = "android.resource://" + packageName + "/" + R.raw.firstvideo
        var videopath2: String = "android.resource://" + packageName + "/" + R.raw.secondvideo

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

    fun loginUser() {

        firebaseAuth.signInWithEmailAndPassword(
            viewBinding.emailUser.text.toString(), viewBinding.passworduser.text.toString()
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                var intent = Intent(this, WelcomeScreen::class.java)

                val handler = Handler()
                handler.postDelayed(Runnable {
                    viewBinding.progressBar.visibility = INVISIBLE
                },3000)
                handler.postDelayed(Runnable {
                    startActivity(intent)
                },2000)
                // here youre are handling theat the user is sing in
                Toast.makeText(this, "User SignIN", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onclicklistnerBS(value: String) {
        if (value.equals("yes")) {
            bottomSheetDialogFragment.dismiss()
            Log.d("GetBack", "MainActivity")
        }
    }


}