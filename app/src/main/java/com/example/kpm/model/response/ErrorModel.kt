package com.example.kpm.model.response

import com.example.kpm.model.response.ErrorStatus

/**
 * Default error model that comes from server if something goes wrong with a repository call
 */
data class ErrorModel(
    val message: String?,
    val code: Int?,
    @Transient var errorStatus: ErrorStatus
) 