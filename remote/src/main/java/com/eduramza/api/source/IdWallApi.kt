package com.eduramza.api.source

import com.eduramza.api.Routes
import com.eduramza.api.model.LoginRequest
import com.eduramza.local.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface IdWallApi{

    @POST(Routes.SIGNUP_ROUTE)
    suspend fun signup(@Body request: LoginRequest): LoginResponse

}