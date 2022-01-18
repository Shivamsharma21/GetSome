package com.example.getsome.ui

import android.util.Log
import androidx.lifecycle.*

class Observer : LifecycleObserver{

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Log.d( "onCreate","This is Observer")
    }

}