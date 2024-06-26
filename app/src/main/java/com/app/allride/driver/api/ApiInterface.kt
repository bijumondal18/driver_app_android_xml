package com.app.allride.driver.api


import com.app.allride.driver.features.auth.login.model.RequestLogin
import com.app.allride.driver.features.auth.login.model.ResponseLogin
import com.app.allride.driver.features.my_trips.model.TripListResponseModel
import com.app.allride.driver.features.trip_details.model.TripDetailsResponseModel
import com.app.allride.driver.utils.AppPreference
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

import java.util.concurrent.TimeUnit

interface ApiInterface {

    companion object Factory {
        var TOKEN = ""

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }

        private fun provideOkHttpClient(): OkHttpClient? {
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
            okHttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS)
            return okHttpClientBuilder.build()
        }
    }


    @POST(Constants.LOGIN_URL)
    fun login(
        @Header("Content-Type") type: String,
        @Header("Accept") accept: String,
        @Header("device") device: String,
        @Body request: RequestLogin
    ): Call<ResponseLogin>

    @GET("${Constants.TRIP_LIST_URL}/{userId}/trips")
    fun tripList(
        @Header("Content-Type") type: String,
        @Header("Accept") accept: String,
        @Header("device") device: String,
        @Header("X-Tenant") xTenant: String,
        @Header("Authorization") token: String,
        @Path("userId") userId: Int,
    ): Call<TripListResponseModel>

    @GET("${Constants.TRIP_DETAILS_URL}/{tripId}")
    fun tripDetails(
        @Header("Content-Type") type: String,
        @Header("Accept") accept: String,
        @Header("device") device: String,
        @Header("X-Tenant") xTenant: String,
        @Header("Authorization") token: String,
        @Path("tripId") tripId: Int,
    ): Call<TripDetailsResponseModel>
}