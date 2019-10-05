package com.eduramza.api

import com.eduramza.local.model.UserPreferences

class UserUseCase(private val prefs: UserPreferences){

    fun isLogged() = prefs.getValueString(prefs.USER_ID) != null

    fun verifyEmailInvalid(localizedMessage: String?): String {
        if (localizedMessage.equals(ERROR_INVALID_EMAIL)){
            return ERROR_INVALID_EMAIL_RESPONSE
        } else {
            return ""
        }
    }

}