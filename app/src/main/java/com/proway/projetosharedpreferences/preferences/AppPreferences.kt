package com.proway.projetosharedpreferences.preferences

import android.content.Context
import androidx.core.content.edit

class AppPreferences(context: Context) {

    private val NAME_PREFERENCE = "app-notifications-preference"
    val base = context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE)

    fun <T> setValue(key: Keys, value: T) {
        when (value) {
            is String -> base.edit {
                putString(key.name, value)
                commit()
            }
            is Boolean -> base.edit {
                putBoolean(key.name, value)
                commit()
            }
        }
    }

    fun somenteNaNovaFeature() {
        println("")
    }



}


enum class Keys(name: String) {
    EMAIL("key_email"),
    EMAIL_NOTIFICATION("key_notification_email"),
    PUSH_NOTIFICATION("key_notification_push")
}