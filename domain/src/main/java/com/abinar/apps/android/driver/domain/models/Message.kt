package com.abinar.apps.android.driver.domain.models

data class Message(
    val russianMessage: String = "",
    val englishMessage: String = "",
    val timestamp: Long = 0,
    val fromUserUid: String = "",
    val toUserUid: String = ""
)