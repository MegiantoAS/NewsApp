package com.megi.newsapp.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.megi.newsapp.R

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_root, rootKey)
        val preference = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        preference?.setOnPreferenceChangeListener { _, value ->
            when(value){
                getString(R.string.pref_dark_on) -> getTheme(DarkMode.ON.value)
                getString(R.string.pref_dark_off) -> getTheme(DarkMode.OFF.value)
                else -> getTheme(DarkMode.AUTO.value)
            }
        }
    }

    private fun getTheme(darkMode: Int): Boolean{
        AppCompatDelegate.setDefaultNightMode(darkMode)
        requireActivity().recreate()
        return true
    }
}