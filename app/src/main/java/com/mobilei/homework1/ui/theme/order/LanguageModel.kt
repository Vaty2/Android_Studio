package com.mobilei.homework1.ui.theme.order

import android.app.LocaleManager
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import java.util.Locale


class LanguageViewModel : ViewModel() {
    private val _currentLanguage = mutableStateOf("en")
    val currentLanguage: State<String> get() = _currentLanguage

    fun switchLanguage() {
        // Toggle language between English and Khmer (you can add more languages as needed)
        _currentLanguage.value = if (_currentLanguage.value == "en") "kh" else "en"

        // Set the locale of the app based on the selected language
        Locale.setDefault(Locale(_currentLanguage.value))
        val configuration = Resources.getSystem().configuration
        configuration.setLocale(Locale(_currentLanguage.value))
        Resources.getSystem().updateConfiguration(configuration, Resources.getSystem().displayMetrics)

        // Trigger the UI to recompose (especially when the app restarts or language is switched)
    }
}

class AppLocaleManager {

    fun changeLanguage(context: Context, languageCode: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(languageCode)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        }
    }

    fun getLanguageCode(context: Context): String {
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)?.applicationLocales?.get(0)
        } else {
            AppCompatDelegate.getApplicationLocales().get(0)
        }
        return locale?.language ?: getDefaultLanguageCode()
    }

    private fun getDefaultLanguageCode(): String {
        return "en" // Default language is English
    }
}



