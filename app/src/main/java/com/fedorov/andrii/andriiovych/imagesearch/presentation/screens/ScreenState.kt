package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

sealed interface ScreenState<out T> {
    object Loading : ScreenState<Nothing>
    data class Error(val throwable: Throwable) : ScreenState<Nothing>
    data class Success<out R>(val value: List<R>) : ScreenState<R>
}