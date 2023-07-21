package com.fedorov.andrii.andriiovych.imagesearch.data.network

import com.fedorov.andrii.andriiovych.imagesearch.data.network.models.ImageModelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("api/")
    suspend fun imageSearch(
        @Query("key") apiKey: String = "35064960-5fb5a27cc028bcb6eeda9eef1",
        @Query("q") name: String,
        @Query("image_type") type: String = "photo",
        @Query("lang") lang: String = "ru",
        @Query("per_page") sizePage: String = "200",
        @Query("page") page: String = "1",
        @Query("orientation") orientation: String = "horizontal"
    ): ImageModelResponse
}