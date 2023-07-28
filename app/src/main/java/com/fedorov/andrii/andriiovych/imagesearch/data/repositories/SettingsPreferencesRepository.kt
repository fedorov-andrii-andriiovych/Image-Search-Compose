package com.fedorov.andrii.andriiovych.imagesearch.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.SettingsPrefRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private const val SETTINGS_PREFERENCE_NAME = "settings_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SETTINGS_PREFERENCE_NAME
)

class SettingsPrefRepositoryImpl @Inject constructor(@ApplicationContext val context: Context) :
    SettingsPrefRepository {

    override val imageOrientationSettings: Flow<String> = context.dataStore.data.map { pref ->
        pref[IMAGE_ORIENTATION_PREF] ?: IMAGE_ORIENTATION_DEFAULT
    }
    override val imageColorSettings: Flow<String> = context.dataStore.data.map { pref ->
        pref[IMAGE_COLOR_PREF] ?: IMAGE_COLOR_DEFAULT
    }
    override val imageSizeSettings: Flow<String> = context.dataStore.data.map { pref ->
        pref[IMAGE_SIZE_PREF] ?: IMAGE_SIZE_DEFAULT
    }

    override suspend fun saveOrientationSettings(value: String) {
        context.dataStore.edit { pref ->
            pref[IMAGE_ORIENTATION_PREF] = value
        }
    }

    override suspend fun saveColorSettings(value: String) {
        context.dataStore.edit { pref ->
            pref[IMAGE_COLOR_PREF] = value
        }
    }

    override suspend fun saveSizeSettings(value: String) {
        context.dataStore.edit { pref ->
            pref[IMAGE_SIZE_PREF] = value
        }
    }

    private companion object {
        private const val IMAGE_ORIENTATION_DEFAULT = "Portrait"
        val IMAGE_ORIENTATION_PREF = stringPreferencesKey("image_orientation")
        private const val IMAGE_COLOR_DEFAULT = "White"
        val IMAGE_COLOR_PREF = stringPreferencesKey("image_color")
        private const val IMAGE_SIZE_DEFAULT = "Medium"
        val IMAGE_SIZE_PREF = stringPreferencesKey("image_size")
    }
}