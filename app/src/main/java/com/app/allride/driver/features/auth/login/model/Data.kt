package com.app.allride.driver.features.auth.login.model
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("isTenantUser")
    val isTenantUser: Boolean,
    @SerializedName("tenantId")
    val tenantId: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_type")
    val userType: String
)