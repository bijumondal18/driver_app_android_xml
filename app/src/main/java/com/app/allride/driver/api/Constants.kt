package com.app.allride.driver.api


object Constants {

    const val API_CONTENT_TYPE = "application/json"
    const val DEVICE_TYPE = "m" //m = mobile


    const val BASE_URL = "https://staging.allrideapps.net/api/v2/"

     // AUTH URLS
    const val LOGIN_URL = "${BASE_URL}central-users/login"
    const val EDIT_PROFILE_URL = "${BASE_URL}drivers"
    const val PROFILE_DETAILS_URL = "${BASE_URL}drivers"


}