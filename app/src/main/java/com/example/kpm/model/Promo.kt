package com.example.kpm.model

data class Promo(
    val name: String,
    val image: String,
    val text: String,
    val link: String,
    val button_text: String,
    val button_color: String,
    val euro_available: Boolean,
    val die_date: Int
)