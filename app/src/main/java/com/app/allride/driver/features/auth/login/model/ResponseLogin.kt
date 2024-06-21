package com.app.allride.driver.features.auth.login.model


import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)