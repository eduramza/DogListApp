package com.eduramza.api.repository

import androidx.lifecycle.MutableLiveData
import com.eduramza.api.UserUseCase
import com.eduramza.api.model.LoginRequest
import com.eduramza.api.source.IdWallApi
import com.eduramza.local.dao.UserDao
import com.eduramza.local.model.LoginResponse
import com.eduramza.local.model.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginRepositoryImpl(private val idWallApi: IdWallApi,
                          private val userDao: UserDao,
                          private val prefs: UserPreferences,
                          private val userCase: UserUseCase) : LoginRepository{

    private val errorInvalidEmail = MutableLiveData<String>()
    override fun getInvalidEmail() = errorInvalidEmail

    private val errorGeneric = MutableLiveData<Boolean>()
    override fun getGenericError() = errorGeneric

    private val successLiveData = MutableLiveData<LoginResponse.User>()
    override fun getSuccess() = successLiveData

    override suspend fun doLogin(email: String) {
        clearError()
        if (email.isNotBlank()){
            if (databaseLogin(email).isNullOrEmpty()){
                try {
                    val result = idWallApi.signup(LoginRequest(email))
                    with(result){
                        saveLoggedUser(this.user)
                    }

                } catch (e: Exception){
                    val isInvalid = userCase.verifyEmailInvalid(e.localizedMessage)
                    if (isInvalid.isBlank()){
                        errorGeneric.postValue(true)
                    }
                    errorInvalidEmail.postValue(isInvalid)
                }
            }
        } else {
            errorInvalidEmail.postValue(userCase.verifyEmailInvalid(email))
        }

    }

    override fun userIsLogged(){
        prefs.clearSharedPreference()
        if (userCase.isLogged()){
            try {
                val dbResult = userDao.getUserLogged(prefs.getValueString(prefs.USER_ID)!!).value
                successLiveData.postValue(dbResult)
            } catch (e: Exception){
                prefs.clearSharedPreference()
                errorGeneric.postValue(true)
            }
        }
    }

    override fun databaseLogin(email: String): String? {
        val result = userDao.getUserSinup(email).value
        if (!result?.id.isNullOrEmpty()){
            saveLoggedUser(result!!)
        }
        return result?.id
    }

    private fun saveLoggedUser(user: LoginResponse.User) = GlobalScope.launch(Dispatchers.IO) {
        successLiveData.postValue(user)
        userDao.insert(user)
        prefs.save(prefs.USER_ID, user.id)
    }

    private fun clearError(){
        errorInvalidEmail.postValue("")
        errorGeneric.postValue(false)
    }

}