package com.example.getsome

import android.content.Intent
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
import com.example.getsome.login.LoginInterface
import com.example.getsome.login.RegisterUserBottomSheet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/* user state is used to identify that is user is new or not
    if the state is = 0 that mean user is new or if user state
    is 1 that mean user is already registered
*/
class LoginActivity : AppCompatActivity(),RegisterUserBottomSheet.BottomsheetListner,LoginInterface {

    lateinit var firebasedatabase:FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    private var bottomSheetDialogFragment = RegisterUserBottomSheet()
    private lateinit var firebaseAuth: FirebaseAuth
    private var state: Int = 1
    private var user_state:Int=1
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        firebasedatabase = FirebaseDatabase.getInstance()
        databaseReference = firebasedatabase.getReference("Users")

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.hide()
        loginbackground() // setting up th login background screens

        viewBinding.googlesignInButton.setOnClickListener {
            Log.d("Login Button","Clicked")
            viewBinding.progressBar.visibility = VISIBLE
            Allowusertologin() // help user to login
        }

        viewBinding.newuser.setOnClickListener {
            user_state = 0
            bottomSheetDialogFragment.show(supportFragmentManager, "Reg")
        }
    }  //// onCreate

    override fun Allowusertologin() {
        firebaseAuth.signInWithEmailAndPassword(
            viewBinding.emailUser.text.toString(), viewBinding.passworduser.text.toString()
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                val handler = Handler()
                handler.postDelayed({
                    viewBinding.progressBar.visibility = INVISIBLE
                },3000)
                handler.postDelayed({
                 takingToDashboard()
                },2000)
                // here you're are handling the the user is sing in
                Toast.makeText(this, "User SignIN", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun loginbackground() {
        val videopath: String = "android.resource://" + packageName + "/" + R.raw.firstvideo
        val videopath2: String = "android.resource://" + packageName + "/" + R.raw.secondvideo

        val uri = Uri.parse(videopath)
        viewBinding.myvideo.setVideoURI(uri)

        viewBinding.myvideo.setOnPreparedListener {
            if (!it.isPlaying) {
                viewBinding.myvideo.start()
            }
        }
        viewBinding.myvideo.start()

        viewBinding.myvideo.setOnCompletionListener {
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
        }
    }

    override fun takingToDashboard() {
              val intent = Intent(this, WelcomeScreen::class.java)
         intent.putExtra("UserState",user_state)
              Log.d("Intent", "PassingState $user_state")
         startActivity(intent)

    }

    override fun onclicklistnerBS(value: String) {
        if (value.equals("yes")) {
            bottomSheetDialogFragment.dismiss()
            Log.d("GetBack", "MainActivity")
        }
    }


}