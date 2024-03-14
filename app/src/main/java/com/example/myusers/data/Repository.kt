package com.example.myusers.data

import com.example.myusers.data.usermodel.ApiResponse

interface Repository {
    suspend fun getUsers(random: Boolean): ApiResponse
}
