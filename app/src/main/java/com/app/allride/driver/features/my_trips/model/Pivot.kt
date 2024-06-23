package com.app.allride.driver.features.my_trips.model

data class Pivot(
    val distance_from_previous_station: Any,
    val duration_from_previous_station: Any,
    val route_id: Int,
    val sequence_number: Int,
    val station_id: Int,
    val station_rate: String,
    val station_time: String
)