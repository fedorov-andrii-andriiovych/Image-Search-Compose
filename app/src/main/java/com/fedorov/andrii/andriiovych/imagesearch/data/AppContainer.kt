package com.fedorov.andrii.andriiovych.imagesearch.data

import com.fedorov.andrii.andriiovych.imagesearch.data.network.ImageService
import com.fedorov.andrii.andriiovych.imagesearch.data.repositories.NetworkRepositoryImpl
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.NetworkRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class  AppContainer{
    private val BASE_URL = "https://pixabay.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ImageService by lazy {
        retrofit.create(ImageService::class.java)
    }

     val networkRepository: NetworkRepository by lazy {
        NetworkRepositoryImpl(retrofitService)
    }

}