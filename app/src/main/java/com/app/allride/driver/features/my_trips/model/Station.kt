package com.app.allride.driver.features.my_trips.model

data class Station(
    val address: String,
    val created_at: String,
    val deleted_at: Any,
    val display_address: String,
    val id: Int,
    val location: Location,
    val name: String,
    val pivot: Pivot,
    val type: Int,
    val updated_at: String
)