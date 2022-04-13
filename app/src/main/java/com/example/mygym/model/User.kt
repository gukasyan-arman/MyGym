package com.example.mygym.model

data class User(
    val id: String?,
    val phone: String?,
    val firstName: String?,
    val lastName: String?,
    val birthday: String?,
    val mail: String?,
    val gender: String?
) {
    fun fullName(): String {
        return "$lastName $firstName"
    }
}