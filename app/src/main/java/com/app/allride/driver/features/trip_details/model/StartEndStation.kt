package com.app.allride.driver.features.trip_details.model

import com.app.allride.driver.features.my_trips.model.Pivot

data class StartEndStation(
    val address: String,
    val created_at: String,
    val deleted_at: Any,
    val display_address: String,
    val dropoff_passengers_count: Int,
    val getup_passengers_count: Int,
    val id: Int,
    val location: Location,
    val name: String,
    val passengers: List<Any>,
    val pivot: Pivot,
    val type: Int,
    val updated_at: String
)