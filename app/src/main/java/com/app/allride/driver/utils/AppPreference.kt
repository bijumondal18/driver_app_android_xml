package com.app.allride.driver.utils

import android.content.Context
import android.content.SharedPreferences

class AppPreference(val context: Context) {

    companion object {
        private const val PREFS_NAME = "com.gozocabs.sessionPref"
        private const val KEY_FIRST_TIME_APP_LOAD = "FIRST_TIME_APP_LOAD"
        private const val KEY_LOGGED_IN = "LOGGED_IN"
        private const val KEY_TENANT_ID = "TENANT_ID"
        private const val KEY_USER_ID = "USER_ID"
        private const val KEY_EMAIL_AND_NUMBER_AVAILABLE = "EMAIL_AND_NUMBER_AVAILABLE"
        private const val KEY_PROFILE_PIC_URL = "PROFILE_PIC_URL"
        private const val KEY_FIRST_NAME = "FIRST_NAME"
        private const val KEY_LAST_NAME = "LAST_NAME"
        private const val KEY_GENDER = "GENDER"
        private const val KEY_AUTH = "sessionId"
        private const val KEY_EMAIL = "EMAIL"
        private const val KEY_CODE = "CODE"
        private const val KEY_NUMBER = "NUMBER"
        private const val KEY_FCM_TOKEN = "FCM_TOKEN"
        private const val KEY_BILLING_ADDRESS = "BILLING_ADDRESS"
        private const val KEY_BILLING_CITY = "BILLING_CITY"
        private const val KEY_BILLING_STATE = "BILLING_STATE"
        private const val KEY_BILLING_PINCODE = "BILLING_PINCODE"
        private const val KEY_BILLING_COUNTRY = "BILLING_COUNTRY"
        private const val KEY_BILLING_ADDRESS_AVAILABLE = "BILLING_ADDRESS_AVAILABLE"
        private const val KEY_SOURCE_CITY = "SOURCE_CITY"
        private const val KEY_SOURCE_CITY_ID = "SOURCE_CITY_ID"
        private const val KEY_DESTINATION_CITY = "DESTINATION_CITY"
        private const val KEY_DESTINATION_ID = "DESTINATION_ID"
        private const val KEY_DAY_RENTAL_CITY = "DAY_RENTAL"
        private const val KEY_DAY_RENTAL_CITY_ID = "DAY_RENTAL_CITY_ID"
        private const val KEY_AIRPORT_CITY = "AIRPORT_CITY"
        private const val KEY_AIRPORT_CITY_ID = "AIRPORT_CITY_ID"
        private const val KEY_AIRPORT_LAT = "AIRPORT_LAT"
        private const val KEY_AIRPORT_LONG = "AIRPORT_LONG"
        private const val KEY_AIRPORT_RADIUS = "AIRPORT_RADIUS"
        private const val KEY_REF_CODE = "REF_CODE"
        private const val KEY_REF_MSG = "REF_MSG"
        private const val KEY_PACKAGE_CITY = "PACKAGE_CITY"
        private const val KEY_PACKAGE_CITY_ID = "PACKAGE_CITY_ID"
        private const val KEY_SOS_BOOKING_ID = "SOS_BOOKING_ID"
        private const val KEY_SOS_TYPE = "SOS_TYPE"
        private const val KEY_SOS_ON = "SOS_ON"
        private const val KEY_REF_ID = "REF_ID"
        private const val KEY_OW_PICKUP_DATE = "OW_PICKUP_DATE"
        private const val KEY_AT_PICKUP_DATE = "AT_PICKUP_DATE"
        private const val KEY_PICKUP_TIME = "PICKUP_TIME"
        private const val KEY_PICKUP_TIME_AMPM = "PICKUP_TIME_AMPM"
        private const val KEY_FIRST_TIME_SHOW_RATING_BOTTOM_SHEET = "KEY_FIRST_TIME_SHOW_RATING_BOTTOM_SHEET"

        private const val KEY_PICKUP_HYPER_LOCATION = "PICKUP_LOCATION"
        private const val KEY_DROP_HYPER_LOCATION = "DROP_LOCATION"
        private const val KEY_IS_ADDRESS_UPDATED = "AUTO_ADDRESS_UPDATED"
        private const val KEY_IS_AIRPORT = "IS_AIRPORT"

        private const val KEY_LOCATION_PERMISSION_ENABLED = "KEY_LOCATION_PERMISSION_ENABLED"
        private const val KEY_STORAGE_PERMISSION_ENABLED = "KEY_STORAGE_PERMISSION_ENABLED"
        private const val KEY_CAMERA_PERMISSION_ENABLED = "KEY_CAMERA_PERMISSION_ENABLED"

        private const val KEY_IS_CAME_FROM_TRAVELLER_SCREEN = "KEY_IS_CAME_FROM_TRAVELLER_SCREEN"

        private const val KEY_FOLLOWUP_ID_FOR_CMB = "KEY_FOLLOWUP_ID_FOR_CMB"

    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun setIsFirstTimeAppLoad(status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_FIRST_TIME_APP_LOAD, status)
        editor.apply()
    }


    fun isFirstTimeAppLoad(): Boolean {
        return sharedPref.getBoolean(KEY_FIRST_TIME_APP_LOAD, true)
    }

    fun setIsFirstTimeShowRating(status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_FIRST_TIME_SHOW_RATING_BOTTOM_SHEET, status)
        editor.apply()
    }


    fun isFirstTimeShowRating(): Boolean {
        return sharedPref.getBoolean(KEY_FIRST_TIME_SHOW_RATING_BOTTOM_SHEET, true)
    }


    fun setIsEmailAndNumberAvailable(status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_EMAIL_AND_NUMBER_AVAILABLE, status)
        editor.apply()
    }

    fun isEmailAndNumberAvailable(): Boolean {
        return sharedPref.getBoolean(KEY_EMAIL_AND_NUMBER_AVAILABLE, false)
    }

    fun setIsLoggedIn(status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_LOGGED_IN, status)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPref.getBoolean(KEY_LOGGED_IN, false)
    }

    fun setTenantId(text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_TENANT_ID, text)
        editor.apply()
    }

    fun getTenantId(): String? {
        return sharedPref.getString(KEY_TENANT_ID, null)
    }

    fun setUserId(value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_USER_ID, value)
        editor.apply()
    }

    fun getUserId(): Int {
        return sharedPref.getInt(KEY_USER_ID, 0)
    }

    fun setProfilePicUrl(text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_PROFILE_PIC_URL, text)
        editor.apply()
    }

    fun getProfilePicUrl(): String? {
        return sharedPref.getString(KEY_PROFILE_PIC_URL, null)
    }


    fun setFirstName(text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_FIRST_NAME, text)
        editor.apply()
    }

    fun getFirstName(): String? {
        return sharedPref.getString(KEY_FIRST_NAME, null)
    }

    /*   fun setGender(text: String) {
           val editor: SharedPreferences.Editor = sharedPref.edit()
           editor.putString(KEY_GENDER, text)
           editor.apply()
       }

       fun getGender(): String? {
           return sharedPref.getString(KEY_GENDER, null)
       }*/

    fun setLastName(text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_LAST_NAME, text)
        editor.apply()
    }

    fun getLastName(): String? {
        return sharedPref.getString(KEY_LAST_NAME, null)
    }

    fun setEmail(text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_EMAIL, text)
        editor.apply()
    }

    fun getEmail(): String? {
        return sharedPref.getString(KEY_EMAIL, null)

    }

    fun setCode(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_CODE, value)
        editor.apply()
    }

    fun getCode(): String? {
        return sharedPref.getString(KEY_CODE, "")

    }

    fun setNumber(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NUMBER, value)
        editor.apply()
    }

    fun getNumber(): String? {
        return sharedPref.getString(KEY_NUMBER, "")

    }

    fun setAuth(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_AUTH, value)
        editor.apply()
    }

    fun getAuth(): String? {
        return sharedPref.getString(KEY_AUTH, "")

    }

    fun setFCMToken(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_FCM_TOKEN, value)
        editor.apply()
    }

    fun getFCMToken(): String? {
        return sharedPref.getString(KEY_FCM_TOKEN, "")

    }

    //ToDO Billing Preference
    fun setBillingAddress(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_BILLING_ADDRESS, value)
        editor.apply()
    }

    fun getBillingAddress(): String? {
        return sharedPref.getString(KEY_BILLING_ADDRESS, "")
    }

    fun setBillingCity(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_BILLING_CITY, value)
        editor.apply()
    }

    fun getBillingCity(): String? {
        return sharedPref.getString(KEY_BILLING_CITY, "")
    }

    fun setBillingState(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_BILLING_STATE, value)
        editor.apply()
    }

    fun getBillingState(): String? {
        return sharedPref.getString(KEY_BILLING_STATE, "")
    }

    fun setBillingPinCode(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_BILLING_PINCODE, value)
        editor.apply()
    }

    fun getBillingPinCode(): String? {
        return sharedPref.getString(KEY_BILLING_PINCODE, "")
    }

    fun setBillingCountry(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_BILLING_COUNTRY, value)
        editor.apply()
    }

    fun getBillingCountry(): String? {
        return sharedPref.getString(KEY_BILLING_COUNTRY, "")
    }

    fun setIsBillingAddressAvailable(status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_BILLING_ADDRESS_AVAILABLE, status)
        editor.apply()
    }

    fun isBillingAddressAvailable(): Boolean {
        return sharedPref.getBoolean(KEY_BILLING_ADDRESS_AVAILABLE, false)
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor.clear()
        editor.apply()
    }

    fun removeValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }

    fun setSourceCity(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_SOURCE_CITY, value)
        editor.apply()
    }

    fun getSourceCity(): String? {
        return sharedPref.getString(KEY_SOURCE_CITY, "Going From")
    }


    fun setSourceCityId(value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_SOURCE_CITY_ID, value)
        editor.apply()
    }

    fun getSourceCityId(): Int {
        return sharedPref.getInt(KEY_SOURCE_CITY_ID, 0)
    }


    fun setDestinationCity(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_DESTINATION_CITY, value)
        editor.apply()
    }

    fun getDestinationCity(): String? {
        return sharedPref.getString(KEY_DESTINATION_CITY, "Going To")
    }

    fun setDestinationId(value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_DESTINATION_ID, value)
        editor.apply()
    }

    fun getDestinationId(): Int {
        return sharedPref.getInt(KEY_DESTINATION_ID, 0)
    }

    fun setDayRentalCity(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_DAY_RENTAL_CITY, value)
        editor.apply()
    }

    //One-Way Pickup date
    fun getOWPickupDate(): Long {
        return sharedPref.getLong(KEY_OW_PICKUP_DATE, 0)
    }

    //One-Way Pickup date
    fun setOWPickupDate(value: Long) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putLong(KEY_OW_PICKUP_DATE, value)
        editor.apply()
    }

    //Airport Transfer Pickup date
    fun getATPickupDate(): Long {
        return sharedPref.getLong(KEY_AT_PICKUP_DATE, 0)
    }

    //Airport Transfer Pickup date
    fun setATPickupDate(value: Long) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putLong(KEY_AT_PICKUP_DATE, value)
        editor.apply()
    }

    fun getPickupTime(): String? {
        return sharedPref.getString(KEY_PICKUP_TIME, null)
    }

    fun setPickupTime(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_PICKUP_TIME, value)
        editor.apply()
    }

    fun getPickupTimeAMPM(): String? {
        return sharedPref.getString(KEY_PICKUP_TIME_AMPM, null)
    }

    fun setPickupTimeAMPM(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_PICKUP_TIME_AMPM, value)
        editor.apply()
    }

    fun getDayRentalCity(): String? {
        return sharedPref.getString(KEY_DAY_RENTAL_CITY, "City")
    }

    fun setDayRentalCityId(value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_DAY_RENTAL_CITY_ID, value)
        editor.apply()

    }

    fun getDatRentalCityId(): Int? {
        return sharedPref.getInt(KEY_DAY_RENTAL_CITY_ID, 0)
    }

    fun setAirportCity(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_AIRPORT_CITY, value)
        editor.apply()
    }

    fun getAirportCity(): String? {
        return sharedPref.getString(KEY_AIRPORT_CITY, "Select Airport")
    }

    fun setAirportCityId(value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_AIRPORT_CITY_ID, value)
        editor.apply()
    }

    fun getAirportCityId(): Int? {
        return sharedPref.getInt(KEY_AIRPORT_CITY_ID, 0)

    }

    fun setAirportLat(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_AIRPORT_LAT, value)
        editor.apply()
    }

    fun getAirportLat(): String? {
        return sharedPref.getString(KEY_AIRPORT_LAT, "0.0")

    }

    fun setAirportLong(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_AIRPORT_LONG, value)
        editor.apply()
    }

    fun getAirportLong(): String? {
        return sharedPref.getString(KEY_AIRPORT_LONG, "0.0")

    }

    fun setAirportRadius(value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_AIRPORT_RADIUS, value)
        editor.apply()
    }

    fun getAirportRadius(): Int? {
        return sharedPref.getInt(KEY_AIRPORT_RADIUS, 0)

    }

    fun setRefCode(text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_REF_CODE, text)
        editor.apply()
    }

    fun getRefCode(): String? {
        return sharedPref.getString(KEY_REF_CODE, null)
    }

    fun setRefMsg(text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_REF_MSG, text)
        editor.apply()
    }

    fun getRefMsg(): String? {
        return sharedPref.getString(KEY_REF_MSG, null)
    }

    fun setPackageCity(value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_PACKAGE_CITY, value)
        editor.apply()
    }

    fun getPackageCity(): String? {
        return sharedPref.getString(KEY_PACKAGE_CITY, "City")
    }

    fun setPackageCityId(value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_PACKAGE_CITY_ID, value)
        editor.apply()
    }

    fun getPackageCityId(): Int? {
        return sharedPref.getInt(KEY_PACKAGE_CITY_ID, 0)
    }

    fun setSOSBookingId(value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_SOS_BOOKING_ID, value)
        editor.apply()
    }

    fun getSOSBookingId(): Int? {
        return sharedPref.getInt(KEY_SOS_BOOKING_ID, 0)
    }

    fun setReferenceId(value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_REF_ID, value)
        editor.apply()
    }

    fun getReferenceId(): Int? {
        return sharedPref.getInt(KEY_REF_ID, 0)
    }


    fun setSOSON(value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_SOS_ON, value)
        editor.apply()
    }

    fun isSOSON(): Boolean? {
        return sharedPref.getBoolean(KEY_SOS_ON, false)
    }

    fun setSOSType(value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_SOS_TYPE, value)
        editor.apply()
    }

    fun getSOSType(): Int? {
        return sharedPref.getInt(KEY_SOS_TYPE, 0)
    }

    fun setPickupAddress(value: String?) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_PICKUP_HYPER_LOCATION, value)
        editor.apply()
    }

    fun getPickupAddress(): String? {
        return sharedPref.getString(KEY_PICKUP_HYPER_LOCATION, "")
    }

    fun setDropAddress(value: String?) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_DROP_HYPER_LOCATION, value)
        editor.apply()
    }

    fun getDropAddress(): String? {
        return sharedPref.getString(KEY_DROP_HYPER_LOCATION, "")
    }

    fun setIsAddressUpdated(status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_IS_ADDRESS_UPDATED, status)
        editor.apply()
    }

    fun isAddressUpdated(): Boolean {
        return sharedPref.getBoolean(KEY_IS_ADDRESS_UPDATED, false)
    }

    fun setIsAirport(status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_IS_AIRPORT, status)
        editor.apply()
    }

    fun isAirport(): Boolean {
        return sharedPref.getBoolean(KEY_IS_AIRPORT, false)
    }


    fun setIsLocationPermissionEnabled(status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_LOCATION_PERMISSION_ENABLED, status)
        editor.apply()
    }

    fun isLocationPermissionEnabled(): Boolean {
        return sharedPref.getBoolean(KEY_LOCATION_PERMISSION_ENABLED, false)
    }

    fun setIsStoragePermissionEnabled(status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_STORAGE_PERMISSION_ENABLED, status)
        editor.apply()
    }

    fun isStoragePermissionEnabled(): Boolean {
        return sharedPref.getBoolean(KEY_STORAGE_PERMISSION_ENABLED, false)
    }

    fun setIsCameraPermissionEnabled(status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_CAMERA_PERMISSION_ENABLED, status)
        editor.apply()
    }

    fun isCameraPermissionEnabled(): Boolean {
        return sharedPref.getBoolean(KEY_CAMERA_PERMISSION_ENABLED, false)
    }

    fun setIsCameFromTravellerScreen(status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_IS_CAME_FROM_TRAVELLER_SCREEN, status)
        editor.apply()
    }

    fun isCameFromTravellerScreen(): Boolean {
        return sharedPref.getBoolean(KEY_IS_CAME_FROM_TRAVELLER_SCREEN, false)
    }

    fun setFollowupId(text: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_FOLLOWUP_ID_FOR_CMB, text)
        editor.apply()
    }

    fun getFollowupId(): Int? {
        return sharedPref.getInt(KEY_FOLLOWUP_ID_FOR_CMB, 0)
    }


}