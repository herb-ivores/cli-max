package com.herbivores.climax.apiclient

import com.google.gson.GsonBuilder
import com.herbivores.climax.constants.WeatherApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiClient {
    private val gson = GsonBuilder().setLenient().create()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}