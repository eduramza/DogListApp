package com.eduramza.doglist.ui.login

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.eduramza.api.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(), LifecycleObserver {

    fun getError() =   loginRepository.getException()
    fun getSuccess() = loginRepository.getSuccess()

    init {
        loginRepository.userIsLogged()
    }

    fun doLogin(email: String) = GlobalScope.launch(Dispatchers.IO){
        loginRepository.doLogin(email)
    }

}
