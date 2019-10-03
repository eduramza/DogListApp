package com.eduramza.local.model

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    val PREFS_FILENAME = "con.ramattec.doglist.user"
    val USER_ID = "user_id"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, value: String) {
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.putString(KEY_NAME, value)

        editor.apply()
    }

    fun getValueString(KEY_NAME: String): String? {

        return prefs.getString(KEY_NAME, null)
    }

    fun clearSharedPreference() {

        val editor: SharedPreferences.Editor = prefs.edit()

        editor.clear()
        editor.apply()
    }
}