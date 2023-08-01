package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.mainscreen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.data.repositories.SettingsPrefRepositoryImpl
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.SettingsPrefRepository
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.DatabaseUseCase
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.ImageSearchUseCase
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.mainscreen.ScreenState.*
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.ImageColor
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.ImageOrientation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageSearchUseCase: ImageSearchUseCase,
    private val databaseUseCase: DatabaseUseCase,
    private val settingsPrefRepository: SettingsPrefRepository
) :
    ViewModel() {
    val imageOrientationState = settingsPrefRepository.imageOrientationSettings.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000), ImageOrientation.PORTRAIT
    )
    private val imageColorState = settingsPrefRepository.imageColorSettings.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000), ImageColor.EMPTY
    )
    val editFieldState = mutableStateOf("")

    private val searchState = MutableStateFlow(RANDOM_SEARCH_STATE)

    val screenState: StateFlow<ScreenState<ImageModel>> = searchState
        .flatMapLatest { searchString ->
            imageSearchUseCase
                .searchImage(
                    name = searchString,
                    color = imageColorState.value.value,
                    size = "",
                    imageOrientationState.value.value
                )
                .map<List<ImageModel>, ScreenState<ImageModel>>(::Success)
                .catch {
                    searchState.emit("")
                    emit(ScreenState.Error(it))
                }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ScreenState.Loading)

    fun searchImage() = viewModelScope.launch {
        searchState.emit(editFieldState.value.ifEmpty { RANDOM_SEARCH_STATE })
    }

    fun saveImageToDatabase(imageModel: ImageModel) = viewModelScope.launch {
        databaseUseCase.insert(imageModel = imageModel)
    }

    companion object {
        private const val RANDOM_SEARCH_STATE = "random"
    }

}

sealed interface ScreenState<out T> {
    object Loading : ScreenState<Nothing>
    data class Error(val throwable: Throwable) : ScreenState<Nothing>
    data class Success<out R>(val value: List<R>) : ScreenState<R>
}