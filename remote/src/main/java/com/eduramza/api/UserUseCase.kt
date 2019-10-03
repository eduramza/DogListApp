package com.eduramza.api

import com.eduramza.local.model.UserPreferences

class UserUseCase(private val prefs: UserPreferences){

    fun isLogged() = prefs.getValueString(prefs.USER_ID) != null

}