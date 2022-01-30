package com.example.getsome.splash
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.getsome.LoginActivity
import com.example.getsome.R

/* this is the splash activity used for showing the
          splash screen
        * */
class SplashActivity : AppCompatActivity(),SplashInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_layout)
    }
    override fun goToLoginActivity() {
        val hanler = Handler()
        hanler.postDelayed(Runnable {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        },3000)
    }
}