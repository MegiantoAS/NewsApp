package com.megi.newsapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.megi.newsapp.di.useCaseModule
import com.megi.newsapp.di.viewModelModule
import com.megi.core.di.dbModule
import com.megi.core.di.networkModule
import com.megi.core.di.repoModule
import com.megi.newsapp.settings.DarkMode
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.*

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    dbModule,
                    networkModule,
                    repoModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        preferenceManager.getString(
            getString(R.string.pref_key_dark),
            getString(R.string.pref_dark_auto)
        )?.apply {
            val darkMode = DarkMode.valueOf(this.uppercase(Locale.UK))
            AppCompatDelegate.setDefaultNightMode(darkMode.value)
        }
    }

}
