package com.example.getsome.api

import com.example.getsome.model.ApiData
import retrofit2.Response
import retrofit2.http.GET

interface ClothService {
    @GET("/cloths")
    suspend fun getClothList():Response<ApiData>

}