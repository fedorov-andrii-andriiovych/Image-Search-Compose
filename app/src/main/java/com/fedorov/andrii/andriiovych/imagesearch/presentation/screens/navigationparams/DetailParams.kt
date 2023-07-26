package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.navigationparams

import android.os.Bundle
import android.os.Parcelable
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import kotlinx.parcelize.Parcelize


private const val PARAMS_KEY = "paramsKey"

@Parcelize
data class DetailParams(
    val list: List<ImageModel> = emptyList(),
    val index: Int = 0,
    val title: String = ""
) : Parcelable

fun DetailParams.toBundle():Bundle{
    return Bundle().apply { putParcelable(PARAMS_KEY,this@toBundle) }
}
fun Bundle.toDetailParams():DetailParams{
    return  this.getParcelable<DetailParams>(PARAMS_KEY)!!
}

