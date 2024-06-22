package com.app.allride.driver.features.my_trips.model

import com.app.allride.driver.features.trip_details.model.Activity
import com.app.allride.driver.features.trip_details.model.Operator
import com.app.allride.driver.features.trip_details.model.TripType

data class TripData(
    val activities: List<Activity>,
    val actual_duration: Any,
    val assigned_driver_id: Int,
    val assigned_route_id: Int,
    val assigned_vehicle_id: Int,
    val assignment_type: Any,
    val created_at: String,
    val created_by: Any,
    val deleted_at: Any,
    val distance_covered: Any,
    val driver: Driver,
    val end_time: Any,
    val geolocations: Any,
    val id: Int,
    val latest_dispatch_activity: Int,
    val number_of_adults: Int,
    val number_of_baggages: Int,
    val number_of_children: Int,
    val number_of_infants: Int,
    val `operator`: Operator,
    val operator_id: Int,
    val passengers_count: Int,
    val reservations: List<Any>,
    val reviews: List<Any>,
    val route: com.app.allride.driver.features.trip_details.model.Route,
    val scheduled_on: String,
    val seat_available: Any,
    val solution_id: Int,
    val startEndStations: List<com.app.allride.driver.features.trip_details.model.StartEndStation>,
    val start_time: Any,
    val trip_reference_number: String,
    val trip_status: Int,
    val trip_type: TripType,
    val updated_at: String,
    val updated_by: Any,
    val vehicle: com.app.allride.driver.features.trip_details.model.Vehicle
)