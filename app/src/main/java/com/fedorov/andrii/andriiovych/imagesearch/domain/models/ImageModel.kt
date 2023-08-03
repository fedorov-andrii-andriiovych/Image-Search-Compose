package com.fedorov.andrii.andriiovych.imagesearch.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageModel(
    val id: Int = 0,
    val landscapeUrl: String = "",
    val portraitUrl: String = ""
) : Parcelable

