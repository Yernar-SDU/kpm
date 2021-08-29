package com.example.kpm.model


data class UserCredentials(
    val login: Int,
    val password: String
){
    override fun toString(): String {
        val a =  "{\n" +
                "  \"login\": ${login},\n" +
                "  \"password\": \"${password}\"\n" +
                "}"
        return a
    }
}
