package com.eduramza.api.source

import com.eduramza.api.Routes
import com.eduramza.api.model.FeedResponse
import com.eduramza.api.model.LoginRequest
import com.eduramza.local.model.LoginResponse
import retrofit2.http.*

interface IdWallApi{

    @POST(Routes.SIGNUP_ROUTE)
    suspend fun signup(@Body request: LoginRequest): LoginResponse

    @GET(Routes.FEED_ROUTE)
    suspend fun getBreed(@Header("Authorization") token: String,
                        @Query("category") category: String): FeedResponse

}