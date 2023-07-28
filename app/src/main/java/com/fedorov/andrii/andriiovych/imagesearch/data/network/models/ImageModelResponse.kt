package com.fedorov.andrii.andriiovych.imagesearch.data.network.models

import com.google.gson.annotations.SerializedName

data class ImageModelResponse(

	@field:SerializedName("next_page")
	val nextPage: String,

	@field:SerializedName("per_page")
	val perPage: Int,

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("photos")
	val photos: List<PhotosItem>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class PhotosItemSize(

	@field:SerializedName("small")
	val small: String,

	@field:SerializedName("original")
	val original: String,

	@field:SerializedName("large")
	val large: String,

	@field:SerializedName("tiny")
	val tiny: String,

	@field:SerializedName("medium")
	val medium: String,

	@field:SerializedName("large2x")
	val large2x: String,

	@field:SerializedName("portrait")
	val portrait: String,

	@field:SerializedName("landscape")
	val landscape: String
)

data class PhotosItem(

	@field:SerializedName("src")
	val photosItemSize: PhotosItemSize,

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("avg_color")
	val avgColor: String,

	@field:SerializedName("alt")
	val alt: String,

	@field:SerializedName("photographer")
	val photographer: String,

	@field:SerializedName("photographer_url")
	val photographerUrl: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("photographer_id")
	val photographerId: Int,

	@field:SerializedName("liked")
	val liked: Boolean,

	@field:SerializedName("height")
	val height: Int
)
