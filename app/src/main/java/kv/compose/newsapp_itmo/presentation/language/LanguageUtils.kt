package kv.compose.newsapp.presentation.language

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kv.compose.newsapp.R
import kv.compose.newsapp.data.repository.PreferenceRepositoryImpl.Companion.LANGUAGE_SYSTEM_DEFAULT

object LanguageUtils {
    private fun getLanguageNumberByCode(languageCode: String): Int =
        languageMap.entries.find { it.value == languageCode }?.key ?: LANGUAGE_SYSTEM_DEFAULT

    fun Int.getLanguageNumber(): Int =
        if (Build.VERSION.SDK_INT >= 33) {
            getLanguageNumberByCode(
                LocaleListCompat.getAdjustedDefault()[0]?.toLanguageTag().toString(),
            )
        } else {
            this
        }

    fun getLanguageConfiguration(languageNumber: Int): String = languageMap.getOrElse(languageNumber) { "" }

    fun setLanguage(locale: String) {
        val localeListCompat = if (locale.isEmpty()) {
            LocaleListCompat.getEmptyLocaleList()
        } else {
            LocaleListCompat.forLanguageTags(locale)
        }

        CoroutineScope(Dispatchers.Main).launch {
            AppCompatDelegate.setApplicationLocales(localeListCompat)
        }
    }

    @Composable
    fun getLanguageDesc(language: Int): String =
        stringResource(
            when (language) {
                ENGLISH -> R.string.la_en
                SWAHILI -> R.string.la_sw
                else -> R.string.follow_system
            },
        )

    // Do not modify
    private const val ENGLISH = 1
    private const val SWAHILI = 2

    // Sorted alphabetically
    val languageMap: Map<Int, String> =
        mapOf(
            ENGLISH to "en",
            SWAHILI to "sw",
        )
}