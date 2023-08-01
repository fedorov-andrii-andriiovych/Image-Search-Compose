package com.fedorov.andrii.andriiovych.imagesearch.domain.repositories

import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.ImageOrientation
import kotlinx.coroutines.flow.Flow

interface SettingsPrefRepository {

  val imageOrientationSettings: Flow<ImageOrientation>
  val imageColorSettings: Flow<String>
  val imageSizeSettings: Flow<String>

  suspend  fun saveOrientationSettings(value:String)
  suspend  fun saveColorSettings(value:String)
  suspend  fun saveSizeSettings(value:String)
}