package com.eduramza.doglist.ui.login

import androidx.lifecycle.*
import com.eduramza.api.repository.login.LoginRepository
import kotlinx.coroutines.*

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(){

    fun getError() =   loginRepository.getInvalidEmail()
    fun getSuccess() = loginRepository.getSuccess()

    private val progress = MutableLiveData<Boolean>()
    fun getProgress() = progress

    init {
        GlobalScope.launch(Dispatchers.IO) {
            progress.postValue(true)
            delay(1000)
            loginRepository.userIsLogged()
            progress.postValue(false)
        }
    }

    fun verifyUserIsLogged() = runBlocking {  }

    fun doLogin(email: String) = GlobalScope.launch(Dispatchers.IO){
        progress.postValue(true)
        delay(1000)
        loginRepository.doLogin(email)
        progress.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        loginRepository.clearSuccessLiveData()
    }

}
