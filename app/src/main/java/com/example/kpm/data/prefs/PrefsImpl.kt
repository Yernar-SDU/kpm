package com.example.kpm.data.prefs

import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject
import kotlin.math.log

class PrefsImpl @Inject constructor(private val gson: Gson,private val preferences: SharedPreferences) :
    Prefs {
    override fun isAuthorized(): Boolean {
        return preferences.getBoolean("auth",false)
    }

    override fun setAuthorized(isAuthorized: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean("auth", isAuthorized)
        editor.apply()
    }

    override fun setTokenPeanut(token: String) {
        val editor = preferences.edit()
        editor.putString("token_peanut", token)
        editor.apply()
    }

    override fun getTokenPeanut(): String {
        return preferences.getString("token_peanut","")!!
    }

    override fun setTokenPartner(token: String) {
        val editor = preferences.edit()
            editor.putString("token_partner",token)
        editor.apply()
    }

    override fun getTokenPartner(): String {
        return preferences.getString("token_partner","")!!
    }

    override fun setLogin(login: String) {
        val editor = preferences.edit()
        editor.putString("login", login)
        editor.apply()
    }

    override fun getLogin(): String {
        return preferences.getString("login","")!!
    }

}