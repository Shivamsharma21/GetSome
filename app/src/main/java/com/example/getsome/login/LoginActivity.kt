package com.example.getsome

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.getsome.databinding.ActivityMainBinding


class LoginActivity : AppCompatActivity() {

     var state:Int = 1

    lateinit var viewmodel:TestViewmodel

    lateinit var viewBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

//        setContentView(viewBinding.root)


           supportActionBar?.hide()
           loginbackground()

        lifecycle.addObserver(Observer())
        viewmodel = ViewModelProvider(this).get(TestViewmodel::class.java)

//        viewmodel.liveData.observe(this, androidx.lifecycle.Observer {
//               viewBinding.textView.text = it
//        })
        viewBinding.mainviewmodel = viewmodel
        viewBinding.lifecycleOwner = this
    }

    fun loginbackground(){
      var videopath:String ="android.resource://"+packageName+"/"+R.raw.shoppingnew
        var videopath2:String ="android.resource://"+packageName+"/"+R.raw.vd31

      var uri = Uri.parse(videopath)
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