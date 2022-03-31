package com.example.getsome.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.getsome.api.ClothService
import com.example.getsome.model.ApiData


class ClothRepository(private val clothService: ClothService) {

    private val clothsList = MutableLiveData<ApiData>()

    val listLiveData:LiveData<ApiData>
    get() = clothsList

     suspend fun getClothsList(){
         val result = clothService.getClothList()
         if (result?.body() != null) {
             clothsList.postValue(result.body())
         }
       Log.d("Result ->",result.body().toString())
     }
}