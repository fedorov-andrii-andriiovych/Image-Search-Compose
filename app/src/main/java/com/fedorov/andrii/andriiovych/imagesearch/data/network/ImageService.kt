package com.fedorov.andrii.andriiovych.imagesearch.data.network

import com.fedorov.andrii.andriiovych.imagesearch.data.network.models.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageService {
    @GET("search")
    suspend fun imageSearch(
        @Header("Authorization") apiKey: String = "7UIIyvKmovOd9HoWQ9hHI97FAC32HZxa3AdX3FFRQfxXtq8SNe3WBGeF",
        @Query("query") name: String = "",
        @Query("orientation") orientation: String = "",
        @Query("size") size: String = "",
        @Query("color") color: String = "",
        @Query("locale") locale: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("per_page")per_page: Int = 80
    ): Response<ImageResponse>
}