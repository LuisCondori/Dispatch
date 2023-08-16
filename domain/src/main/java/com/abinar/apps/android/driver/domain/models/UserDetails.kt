package com.abinar.apps.android.driver.domain.models

data class UserDetails(
    var uid: String = "",
    var fullname: String = "",
    var email: String = "",
    var password: String = "",
    var photoProfileUrl: String = ""
)