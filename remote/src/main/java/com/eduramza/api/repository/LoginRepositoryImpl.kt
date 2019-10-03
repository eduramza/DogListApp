package com.eduramza.api.repository

import androidx.lifecycle.MutableLiveData
import com.eduramza.api.UserUseCase
import com.eduramza.api.model.LoginRequest
import com.eduramza.api.source.IdWallApi
import com.eduramza.local.dao.UserDao
import com.eduramza.local.model.LoginResponse
import com.eduramza.local.model.UserPreferences

class LoginRepositoryImpl(private val idWallApi: IdWallApi,
                          private val userDao: UserDao,
                          private val prefs: UserPreferences,
                          private val userCase: UserUseCase) : LoginRepository{

    private val errorLiveData = MutableLiveData<String>()
    override fun getException() = errorLiveData

    private val successLiveData = MutableLiveData<LoginResponse.User>()
    override fun getSuccess() = successLiveData

    override suspend fun doLogin(email: String) {
        clearError()
        try {
            val result = idWallApi.signup(LoginRequest(email))
            successLiveData.postValue(result.user)
            userDao.insert(result.user)

            prefs.save(prefs.USER_ID, result.user.id)

        } catch (e: Exception){
            errorLiveData.postValue(e.localizedMessage)
        }
    }

    override fun userIsLogged(){
        if (userCase.isLogged()){
            try {
                val dbResult = userDao.getUser(prefs.getValueString(prefs.USER_ID)!!).value
                successLiveData.postValue(dbResult)
            } catch (e: Exception){
                prefs.clearSharedPreference()
                e.printStackTrace()
            }
        }
    }

    fun clearError(){
        errorLiveData.postValue("")
    }

}