package com.example.getsome.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClothHelper {

                                // this Url Refer to the heroku webservice endpoint
                                // avoid accessing it with alone because base url didn't
                                // had a response body

    private const val BASE_URL ="https://getsomebackend.herokuapp.com/"

    fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}