package com.fedorov.andrii.andriiovych.imagesearch.presentation.common

sealed interface ScreenState<out T> {
    object Loading : ScreenState<Nothing>
    data class Error(val error: ErrorText) : ScreenState<Nothing>
    data class Success<out R>(val value: List<R>) : ScreenState<R>
}