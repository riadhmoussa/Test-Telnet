package com.moussa.componentdata.data.remote

import com.moussa.componentdata.data.remote.dto.WeatherDetailDto
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherPaprikaApi {


    @GET("/data/2.5/weather")
    suspend fun getTownWeather(@Query("q") q: String, @Query("APPID") APPID: String): WeatherDetailDto
}