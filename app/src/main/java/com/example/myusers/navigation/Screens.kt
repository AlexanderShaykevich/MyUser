package com.example.myusers.navigation

const val ARGUMENT_KEY = "email"

sealed class Screens(val route: String) {
    object Users: Screens("users")
    object UserDetails: Screens("users_details/{$ARGUMENT_KEY}") {
        fun passArg(email: String): String = "users_details/$email"
    }
}
