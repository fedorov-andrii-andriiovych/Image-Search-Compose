package com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.DatabaseUseCase
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.ImageSearchUseCase
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.ScreenState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageSearchUseCase: ImageSearchUseCase,
    private val databaseUseCase: DatabaseUseCase
) :
    ViewModel() {

    val editFieldState = mutableStateOf(" ")

    private val searchState = MutableStateFlow("nature")

    val screenState: StateFlow<ScreenState<ImageModel>> = searchState
        .flatMapLatest { searchString ->
            imageSearchUseCase
                .searchImage(searchString)
                .map<List<ImageModel>, ScreenState<ImageModel>>(::Success)
//                .catch {
//                    searchState.emit("")
//                    emit(ScreenState.Error(it))
//                }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ScreenState.Loading)

     fun searchImage() = viewModelScope.launch {
        searchState.emit(editFieldState.value)
    }

    fun saveImageToDatabase(imageModel: ImageModel) = viewModelScope.launch {
        databaseUseCase.insert(imageModel = imageModel)
    }

}

sealed interface ScreenState<out T> {
    object Loading : ScreenState<Nothing>
    data class Error(val throwable: Throwable) : ScreenState<Nothing>
    data class Success<out R>(val value: List<R>) : ScreenState<R>
}