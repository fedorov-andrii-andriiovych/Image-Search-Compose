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

    override val screenOrientationSettings: Flow<String> = context.dataStore.data.map { pref ->
        pref[SCREEN_ORIENTATION_PREF] ?: SCREEN_ORIENTATION_DEFAULT
    }

    override suspend fun saveOrientationSettings(value: String) {
        context.dataStore.edit { pref ->
            pref[SCREEN_ORIENTATION_PREF] = value
        }
    }

    private companion object {
        private const val SCREEN_ORIENTATION_DEFAULT = "Portrait"
        val SCREEN_ORIENTATION_PREF = stringPreferencesKey("screen_orientation")
    }


}