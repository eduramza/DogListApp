package com.eduramza.doglist.app

import androidx.room.Room
import com.eduramza.api.UserUseCase
import com.eduramza.api.repository.LoginRepository
import com.eduramza.api.repository.LoginRepositoryImpl
import com.eduramza.api.source.IdWallService
import com.eduramza.doglist.ui.login.LoginViewModel
import com.eduramza.local.database.DogListDatabase
import com.eduramza.local.model.UserPreferences
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { IdWallService().getRemoteService() }

    viewModel { LoginViewModel(get()) }

    single { UserPreferences(get()) }

    single { UserUseCase(get()) }

    single<LoginRepository> { LoginRepositoryImpl(get(), get(), get(), get())}
}

val localDbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            DogListDatabase::class.java,
            "doglist-app"
        ).build()
    }

    factory { get<DogListDatabase>().userDao() }

}