package com.app.allride.driver.api


import com.app.allride.driver.features.auth.login.model.RequestLogin
import com.app.allride.driver.features.auth.login.model.ResponseLogin
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

import java.util.concurrent.TimeUnit

interface ApiInterface {
    companion object Factory {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .client(provideOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl(Constants.BASE_URL)
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

}