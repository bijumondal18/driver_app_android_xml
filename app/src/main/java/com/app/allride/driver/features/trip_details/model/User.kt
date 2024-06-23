package com.app.allride.driver.features.trip_details.model

data class User(
    val avatar: String,
    val created_at: String,
    val deleted_at: Any,
    val email: String,
    val email_verified_at: String,
    val first_name: String,
    val gender: Int,
    val global_id: String,
    val google_id: Any,
    val id: Int,
    val is_active: Int,
    val lang: String,
    val last_name: String,
    val operator_id: Any,
    val parent_id: Any,
    val phone: String,
    val qr_link: Any,
    val temp_email: Any,
    val temp_phone: Any,
    val updated_at: String,
    val user_type_id: Int
)