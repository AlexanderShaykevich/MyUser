package com.example.myusers.data

import android.content.Context
import com.example.myusers.api.UserApi
import com.example.myusers.data.usermodel.ApiResponse

const val SEED_KEY = "seed"

class RepositoryImpl(
    private val api: UserApi,
    context: Context
) : Repository {
    private val prefs = context.getSharedPreferences("seed", Context.MODE_PRIVATE)

    override suspend fun getUsers(random: Boolean): ApiResponse {
        val seed = prefs.getString(SEED_KEY, "") ?: ""
        val response = if (random) api.getUsers() else api.getUsers(seed = seed)
        if (response.isSuccessful) {
            if (seed.isBlank() || random) response.body()?.info?.seed?.let { saveSeed(it) }
        } else throw RuntimeException(response.message())

        return response.body() ?: throw RuntimeException(response.message())
    }

    private fun saveSeed(seed: String) {
        prefs.edit().putString(SEED_KEY, seed).apply()
    }

}

