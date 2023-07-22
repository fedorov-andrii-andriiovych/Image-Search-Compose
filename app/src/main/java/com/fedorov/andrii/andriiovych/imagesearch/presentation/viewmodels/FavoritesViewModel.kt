package com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.DatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val databaseUseCase: DatabaseUseCase) :
    ViewModel() {

    private val _stateFavoriteList = databaseUseCase.getAll()
    val stateFavoriteList = _stateFavoriteList

    fun deleteImage(imageModel: ImageModel) = viewModelScope.launch {
        databaseUseCase.delete(imageModel = imageModel)
    }
}