package com.example.getsome.ui

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.getsome.R
import com.example.getsome.databinding.ActivityMainBinding


class LoginActivity : AppCompatActivity() {

     var state:Int = 1
    
    lateinit var viewBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)
           supportActionBar?.hide()
        loginbackground()

        lifecycle.addObserver(Observer())

            }

  fun loginbackground(){
      var videopath:String ="android.resource://"+packageName+"/"+R.raw.shoppingnew
        var videopath2:String ="android.resource://"+packageName+"/"+R.raw.vd31

      var uri = Uri.parse(videopath)
    //    var uri1 = Uri.parse(videopath2)
      viewBinding.myvideo.setVideoURI(uri)

      viewBinding.myvideo.setOnPreparedListener {
          if (!it.isPlaying){
              viewBinding.myvideo.start()
          }
      }
      viewBinding.myvideo.start()


      Log.d("onCreate","Activity")

      Toast.makeText(this,"text",Toast.LENGTH_LONG).show()

      viewBinding.myvideo.setOnCompletionListener(MediaPlayer.OnCompletionListener {
          if (state ==1){
              it.reset() // second vid
              viewBinding.myvideo.setVideoPath(videopath2)
              viewBinding.myvideo.start()
              state=0
          }else if(state ==0){
              it.reset()
              viewBinding.myvideo.setVideoPath(videopath)
              viewBinding.myvideo.start()
              state =1
          }
      })
  }

}