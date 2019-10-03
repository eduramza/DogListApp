package com.eduramza.api.source

import com.eduramza.api.Routes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IdWallService {

    fun getRemoteService(): IdWallApi{
        val retrofit = Retrofit.Builder()
            .baseUrl(Routes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(IdWallApi::class.java)
    }

}