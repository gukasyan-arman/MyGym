package com.example.mygym.model

data class Coach(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val description: String = "",
    val post: String = ""
) {
    fun fullName(): String {
        return "$lastName $firstName"
    }
}
