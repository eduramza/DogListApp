package com.eduramza.api.repository

import androidx.lifecycle.MutableLiveData
import com.eduramza.local.model.LoginResponse

interface LoginRepository {
    suspend fun doLogin(email: String)
    fun getInvalidEmail(): MutableLiveData<String>
    fun getSuccess(): MutableLiveData<LoginResponse.User>
    fun userIsLogged()
    fun databaseLogin(email: String): String?
    fun getGenericError(): MutableLiveData<Boolean>
}