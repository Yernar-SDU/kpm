package com.example.kpm.model

import java.io.Serializable

data class UserData(
    val address: String = "",
    val balance: String = "",
    val city: String = "",
    val country: String = "",
    val currency: Int = 0,
    val currentTradesCount: Int = 0,
    val currentTradesVolume: Int = 0,
    val equity: String = "",
    val freeMargin: String = "",
    val isAnyOpenTrades: String = "",
    val isSwapFree: String = "",
    val leverage: Int = 0,
    val name: String = "",
    val phone: String = "",
    val totalTradesCount: Int = 0,
    val totalTradesVolume: Int = 0,
    val type: Int = 0,
    val verificationLevel: Int = 0,
    val zipCode: String  = ""
): Serializable {
}