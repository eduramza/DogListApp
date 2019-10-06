package com.eduramza.local.model

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    val PREFS_FILENAME = "con.ramattec.doglist.user"
    val USER_TOKEN = "user_token"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var token: String?
    get() = prefs.getString(USER_TOKEN, "")
    set(value) = prefs.edit().putString(USER_TOKEN, value).apply()

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.clear()
        editor.apply()
    }
}