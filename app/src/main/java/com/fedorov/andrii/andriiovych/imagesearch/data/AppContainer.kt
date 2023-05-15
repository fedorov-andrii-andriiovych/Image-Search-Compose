package com.fedorov.andrii.andriiovych.imagesearch.data

import com.fedorov.andrii.andriiovych.imagesearch.network.ImageService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class  AppContainer{
    private val BASE_URL = "https://www.googleapis.com/books/v1/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ImageService by lazy {
        retrofit.create(ImageService::class.java)
    }

     val imageRepository: ImageRepository by lazy {
        NetworkImageRepository(retrofitService)
    }

}