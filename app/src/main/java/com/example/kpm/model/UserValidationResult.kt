package com.example.kpm.model

import java.util.*

data class UserValidationResult(
    val extensionData: ExtenstionData = ExtenstionData("as"),
    val result: Boolean = false,
    val token: String = ""
)