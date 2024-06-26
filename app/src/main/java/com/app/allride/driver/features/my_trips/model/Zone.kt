package com.app.allride.driver.features.my_trips.model

import com.app.allride.driver.features.trip_details.model.Location

data class Zone(
    val contact_email: Any,
    val contact_name: Any,
    val contact_phone: Any,
    val created_at: String,
    val created_by: Int,
    val deleted_at: Any,
    val description: Any,
    val fare: Any,
    val fare_id: Any,
    val id: Int,
    val location: Location,
    val name: String,
    val radius: String,
    val updated_at: String,
    val updated_by: Any
)