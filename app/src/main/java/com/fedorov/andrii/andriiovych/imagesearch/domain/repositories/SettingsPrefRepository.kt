package com.fedorov.andrii.andriiovych.imagesearch.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SettingsPrefRepository {

  val imageOrientationSettings: Flow<String>
  val imageColorSettings: Flow<String>
  val imageSizeSettings: Flow<String>

  suspend  fun saveOrientationSettings(value:String)
  suspend  fun saveColorSettings(value:String)
  suspend  fun saveSizeSettings(value:String)
}