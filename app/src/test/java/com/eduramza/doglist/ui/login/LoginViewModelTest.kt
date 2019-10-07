package com.eduramza.doglist.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.eduramza.api.ERROR_EMAIL_IS_EMPTY_RESPONSE
import com.eduramza.api.ERROR_INVALID_EMAIL_RESPONSE
import com.eduramza.api.repository.feed.FeedRepository
import com.eduramza.api.repository.login.LoginRepository
import com.eduramza.doglist.ManagedCoroutineScope
import com.eduramza.doglist.TestScope
import com.eduramza.local.model.LoginResponse
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class LoginViewModelTest: KoinTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val managedCoroutineScope: ManagedCoroutineScope = TestScope(testDispatcher)

    private val loginViewModel: LoginViewModel by inject()

    @Mock
    private lateinit var repository: LoginRepository

    @Before
    fun before(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(
                module {
                    single { LoginViewModel(repository) }
                }
            )
        }
    }

    @After
    fun after(){
        stopKoin()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `Should call method that will validate if User Already Is Logged`()
            = runBlockingTest (managedCoroutineScope.coroutineContext){

        loginViewModel.verifyIfUserIsLogged()

        verify(repository).userIsLogged()
        val progress = loginViewModel.getProgress()
        assertFalse(progress.value!!)
    }

    @Test
    fun `Do Login`() = runBlockingTest (managedCoroutineScope.coroutineContext){

        loginViewModel.doLogin("ramza@gmail.com")

        verify(repository).doLogin(anyString())
        val progress = loginViewModel.getProgress()
        assertFalse(progress.value!!)
    }


    @Test
    fun `getError Invalid E-mail`() = runBlockingTest (managedCoroutineScope.coroutineContext) {

        val livedata = MutableLiveData<String>()
        livedata.postValue(ERROR_INVALID_EMAIL_RESPONSE)

        whenever(repository.getInvalidEmail()).thenReturn(livedata)

        //action
        loginViewModel.doLogin("sdeasd")
        val result = loginViewModel.getError()

        verify(repository).doLogin(anyString())
        assertEquals("E-mail inválido!", result.value)
    }

    @Test
    fun `getError Empty E-mail`() = runBlockingTest (managedCoroutineScope.coroutineContext) {

        val livedata = MutableLiveData<String>()
        livedata.postValue(ERROR_EMAIL_IS_EMPTY_RESPONSE)

        whenever(repository.getInvalidEmail()).thenReturn(livedata)

        //action
        loginViewModel.doLogin("")
        val result = loginViewModel.getError()

        verify(repository).doLogin(anyString())
        assertEquals("Este campo deve ser preenchido", result.value)
    }

    @Test
    fun `getSuccess because the E-mail is Correct`() = runBlockingTest (managedCoroutineScope.coroutineContext) {

        val user = LoginResponse.User("10-10-2010", "ramza@gmail.com", "sad",
            "6wda21", "10-10-10", 1)
        val livedata = MutableLiveData<LoginResponse.User>()
        livedata.postValue(user)

        whenever(repository.getSuccess()).thenReturn(livedata)

        //action
        loginViewModel.doLogin("ramza@gmail.com")
        val result = loginViewModel.getSuccess()

        verify(repository).doLogin(anyString())
        assertEquals(user, result.value)
    }


}