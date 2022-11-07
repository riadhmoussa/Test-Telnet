package com.moussa.componentdata.domain.repository

import com.moussa.componentdata.data.remote.dto.WeatherDetailDto


interface WeatherRepository {

    suspend fun getTownWeather(name: String,appId:String): WeatherDetailDto
}