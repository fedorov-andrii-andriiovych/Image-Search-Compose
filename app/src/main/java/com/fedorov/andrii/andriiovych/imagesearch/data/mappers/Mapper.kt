package com.fedorov.andrii.andriiovych.imagesearch.data.mappers

interface Mapper<F,T> {

    fun mapFrom(from:F):T

}