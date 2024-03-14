package com.example.myusers.data.usermodel

data class User(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val name: Name,
    val phone: String,
    val picture: Picture,
)