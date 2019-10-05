package com.eduramza.doglist.ui.login

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduramza.api.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(), LifecycleObserver {

    fun getError() =   loginRepository.getInvalidEmail()
    fun getSuccess() = loginRepository.getSuccess()

    private val progress = MutableLiveData<Boolean>()
    fun getProgress() = progress

    init {
        loginRepository.userIsLogged()
    }

    fun doLogin(email: String) = GlobalScope.launch(Dispatchers.IO){
        progress.postValue(true)
        delay(1000)
        loginRepository.doLogin(email)
        progress.postValue(false)
    }

}
