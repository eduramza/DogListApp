package com.eduramza.api

import android.util.Log
import com.eduramza.local.model.UserPreferences

class UserUseCase(private val prefs: UserPreferences){

    fun isLogged(): String?{
        Log.d("UserSession", prefs.token)
        return prefs.token
    }

    fun verifyEmailInvalid(message: String?) = when(message){
            ERROR_INVALID_EMAIL -> ERROR_INVALID_EMAIL_RESPONSE
            ERROR_EMAIL_IS_EMPTY -> ERROR_EMAIL_IS_EMPTY_RESPONSE
            else -> ""
        }

}