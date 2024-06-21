package com.app.allride.driver.features.auth.login.model


import com.google.gson.annotations.SerializedName

data class RequestLogin(
    @SerializedName("app_version")
    val appVersion: String,
    @SerializedName("device_platform")
    val devicePlatform: String,
    @SerializedName("device_token")
    val deviceToken: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)