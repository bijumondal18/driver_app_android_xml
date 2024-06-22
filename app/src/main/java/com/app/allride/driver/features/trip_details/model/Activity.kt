package com.app.allride.driver.features.trip_details.model

data class Activity(
    val activity: Int,
    val created_at: String,
    val customer_id: Any,
    val deleted_at: Any,
    val dispatch_id: Int,
    val driver_id: Int,
    val id: Int,
    val note: Any,
    val reservation_id: Any,
    val updated_at: String
)