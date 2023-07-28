package com.fedorov.andrii.andriiovych.imagesearch.domain.repositories

import com.fedorov.andrii.andriiovych.imagesearch.domain.models.SettingsParams
import kotlinx.coroutines.flow.Flow

interface SettingsPrefRepository {

  val screenOrientationSettings: Flow<String>

  suspend  fun saveOrientationSettings(value:String)
}