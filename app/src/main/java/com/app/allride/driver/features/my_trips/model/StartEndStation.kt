package com.app.allride.driver.features.my_trips.model

import com.app.allride.driver.features.trip_details.model.Location

data class StartEndStation(
    val address: String,
    val created_at: String,
    val deleted_at: Any,
    val display_address: String,
    val id: Int,
    val location: Location,
    val name: String,
//    val pivot: PivotX,
    val type: Int,
    val updated_at: String
)