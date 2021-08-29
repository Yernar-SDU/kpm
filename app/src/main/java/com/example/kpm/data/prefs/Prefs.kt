package com.example.kpm.data.prefs

import java.util.*


interface Prefs {
    fun isAuthorized(): Boolean
    fun setAuthorized(isAuthorized: Boolean)

    fun setTokenPeanut(token: String)
    fun getTokenPeanut(): String

    fun setTokenPartner(token: String)
    fun getTokenPartner(): String

    fun setLogin(login: String)
    fun getLogin(): String

}