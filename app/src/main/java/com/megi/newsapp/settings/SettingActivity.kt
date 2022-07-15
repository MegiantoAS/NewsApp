package com.megi.newsapp.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.megi.newsapp.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingFragment())
                .commit()
        }
        supportActionBar?.title = getString(R.string.Setting)
    }
}