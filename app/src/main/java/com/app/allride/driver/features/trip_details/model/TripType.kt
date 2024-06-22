package com.app.allride.driver.features.trip_details.model

data class TripType(
    val deleted_at: Any,
    val id: Int,
    val name: String,
    val parent_id: Int,
    val product_type: Int,
    val slug: String
)