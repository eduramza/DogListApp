package com.eduramza.doglist.app

import androidx.room.Room
import com.eduramza.api.BreedsUseCase
import com.eduramza.api.UserUseCase
import com.eduramza.api.repository.feed.FeedRepository
import com.eduramza.api.repository.feed.FeedRepositoryImpl
import com.eduramza.api.repository.login.LoginRepository
import com.eduramza.api.repository.login.LoginRepositoryImpl
import com.eduramza.api.source.IdWallService
import com.eduramza.doglist.ui.home.dogs.DogImagesViewModel
import com.eduramza.doglist.ui.login.LoginViewModel
import com.eduramza.local.database.DogListDatabase
import com.eduramza.local.model.UserPreferences
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { LoginViewModel(get()) }
    viewModel { DogImagesViewModel(get()) }

}

val remoteDbModule = module{
    single { IdWallService().getRemoteService() }

    single<LoginRepository> {
        LoginRepositoryImpl(
            get(),
            get(),
            get(),
            get()
        )
    }

    single<FeedRepository>{
        FeedRepositoryImpl(
            get(),
            get()
        )
    }
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

    single { UserPreferences(get()) }
    single { UserUseCase(get()) }
    single { BreedsUseCase(get(), get() ) }

}