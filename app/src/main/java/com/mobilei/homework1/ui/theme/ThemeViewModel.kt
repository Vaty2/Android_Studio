package com.mobilei.homework1.ui.theme

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    private val _isDarkMode = MutableLiveData<Boolean>()
    val isDarkMode: LiveData<Boolean> = _isDarkMode

    private val darkModeKey = "darkMode"

    init {
        // Load stored value
        _isDarkMode.value = sharedPreferences.getBoolean(darkModeKey, false)
    }

    fun toggleDarkMode() {
        val newDarkMode = !(_isDarkMode.value ?: false)
        _isDarkMode.value = newDarkMode

        // Save the new state to SharedPreferences
        sharedPreferences.edit().putBoolean(darkModeKey, newDarkMode).apply()
    }
}
