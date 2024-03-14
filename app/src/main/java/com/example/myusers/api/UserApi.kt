package com.example.myusers.api

import com.example.myusers.data.usermodel.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val DEFAULT_COUNT = 100

interface UserApi {
    @GET(".")
    suspend fun getUsers(
        @Query("results") count: Int = DEFAULT_COUNT,
        @Query("seed") seed: String = "",
    ): Response<ApiResponse>
}