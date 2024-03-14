package com.example.myusers.data.usermodel

data class Location(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val street: Street,
)