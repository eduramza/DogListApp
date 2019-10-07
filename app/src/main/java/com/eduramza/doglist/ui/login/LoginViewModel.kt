package com.eduramza.doglist.ui.login

import androidx.lifecycle.*
import com.eduramza.api.repository.login.LoginRepository
import kotlinx.coroutines.*

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(), LifecycleObserver{

    fun getError() =   loginRepository.getInvalidEmail()
    fun getSuccess() = loginRepository.getSuccess()

    private val progress = MutableLiveData<Boolean>()
    fun getProgress() = progress

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun verifyIfUserIsLogged() = runBlocking (Dispatchers.IO) {
        progress.postValue(true)
//        delay(1000)
        loginRepository.userIsLogged()
        progress.postValue(false)
    }

    fun doLogin(email: String) = runBlocking (Dispatchers.IO){
        progress.postValue(true)
//        delay(1000) for visualize the loading animation
        loginRepository.doLogin(email)
        progress.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        loginRepository.clearSuccessLiveData()
    }

}
