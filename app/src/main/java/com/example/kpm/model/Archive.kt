package com.example.kpm.model

data class Archive(
    val Id: Int,
    val ActualTime: String,
    val Comment: String,
    val Pair: String,
    val Cmd: Int,
    val TradingSystem: Int,
    val Period: String,
    val Price: Double,
    val SL: Double,
    val Tp: String
)