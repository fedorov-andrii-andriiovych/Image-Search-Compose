package com.fedorov.andrii.andriiovych.imagesearch.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageModel (val url:String = "", val id:Int = 0):Parcelable

