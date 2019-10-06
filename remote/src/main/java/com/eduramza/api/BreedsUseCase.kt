package com.eduramza.api

import com.eduramza.local.dao.UserDao
import com.eduramza.local.model.UserPreferences

class BreedsUseCase(private val prefs: UserPreferences,
                    private val userDao: UserDao
) {

}