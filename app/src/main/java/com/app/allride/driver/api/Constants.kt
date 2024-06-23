package com.app.allride.driver.api


object Constants {

    const val API_CONTENT_TYPE = "application/json"
    const val DEVICE_TYPE = "m" //m = mobile
    const val API_DATE_FORMAT = "dd MMM"
    const val API_TIME_FORMAT = "HH:mm a"


    const val BASE_URL = "https://staging.allrideapps.net/api/v2/"

     // AUTH URLS
    const val LOGIN_URL = "${BASE_URL}central-users/login" //done
    const val EDIT_PROFILE_URL = "${BASE_URL}drivers"
    const val PROFILE_DETAILS_URL = "${BASE_URL}drivers"

    const val CHANGE_PHONE_URL = "${BASE_URL}users"
    const val CHANGE_PASSWORD_URL = "${BASE_URL}users/change_password"
    const val FORGET_PASSWORD_URL = "${BASE_URL}central-users/forgot-password"

    //StartUp
    const val STARTUP_API_URL = "${BASE_URL}startup"

    //Vehicle
    const val VEHICLE_LIST_URL = "${BASE_URL}drivers"
    const val updateVehicle = "${BASE_URL}vehicles"
    const val vehicleType = "${BASE_URL}vehicle_types"
    const val vehicleMake = "${BASE_URL}vehicle_makes"
    const val vehicleRateCard = "${BASE_URL}vehicle_rate_card"
    const val addVehicle = "${BASE_URL}drivers"
    const val deleteVehicle = "${BASE_URL}vehicles"

    //Trip
    const val TRIP_LIST_URL = "${BASE_URL}drivers"
    const val TRIP_DETAILS_URL = "${BASE_URL}trips"
    const val updateTripStatus = "${BASE_URL}trips"
    const val addFeedback = "${BASE_URL}reviews"

    //Ride Hailing
    const val acceptReservation = "${BASE_URL}ridehailing_transport/accept_reservation";

    //Support or Ticket
    const val ticketList = "${BASE_URL}supports"
    const val supportType = "${BASE_URL}support_categories"
    const val addSupport = "${BASE_URL}supports"

    //Bank Accounts
    const val bankAccountList = "${BASE_URL}drivers"
    const val addBankAccount = "${BASE_URL}drivers"
    const val deleteBankAccount = "${BASE_URL}drivers/bank"

    //Settings By Key
    const val cancelReasonList = "${BASE_URL}settings/by_key"
    const val faqList = "${BASE_URL}settings/by_key"

    //Notification
    const val notification = "${BASE_URL}notifications";

}