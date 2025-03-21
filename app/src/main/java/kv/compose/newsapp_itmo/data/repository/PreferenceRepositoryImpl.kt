package kv.compose.newsapp.data.repository

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kv.compose.newsapp.data.utils.Constants
import kv.compose.newsapp.domain.repository.PreferenceRepository

class PreferenceRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : PreferenceRepository {
    override suspend fun saveTheme(themeValue: Int) {
        dataStore.edit { preferences ->
            preferences[Constants.THEME_OPTIONS] = themeValue
        }
    }

    override fun getTheme(): Flow<Int> = dataStore.data.map { preferences ->
        preferences[Constants.THEME_OPTIONS] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

    override suspend fun setLanguage(languageNumber: Int) {
        dataStore.edit { preferences ->
            preferences[Constants.LANGUAGE_KEY] = languageNumber
        }
    }

    override fun getLanguage(): Flow<Int> =
        dataStore.data.map { preferences ->
            preferences[Constants.LANGUAGE_KEY] ?: LANGUAGE_SYSTEM_DEFAULT
        }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        const val LANGUAGE_SYSTEM_DEFAULT = 0
    }
}