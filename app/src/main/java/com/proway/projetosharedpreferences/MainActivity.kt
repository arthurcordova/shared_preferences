package com.proway.projetosharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.proway.projetosharedpreferences.preferences.AppPreferences
import com.proway.projetosharedpreferences.preferences.Keys
import com.proway.projetosharedpreferences.ui.main.MainFragment
import com.proway.projetosharedpreferences.ui.main.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        preferences = AppPreferences(this)
        val savedEmail = preferences.base.getString(Keys.EMAIL.name, "")
        if (savedEmail.isNullOrEmpty()) {
            replaceFragment(MainFragment.newInstance())
        } else {
            replaceFragment(SettingsFragment.newInstance())
        }

    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }
}