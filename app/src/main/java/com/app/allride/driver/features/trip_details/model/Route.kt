package com.app.allride.driver.features.trip_details.model

data class Route(
    val created_at: String,
    val deleted_at: Any,
    val destination_address: String,
    val destination_location: DestinationLocation,
    val id: Int,
    val name: String,
    val origin_address: String,
    val origin_location: OriginLocation,
    val route_type: Int,
    val stations: List<Station>,
    val total_distance: Any,
    val total_duration: Any,
    val updated_at: String,
    val zone: Zone,
    val zone_id: Int
)