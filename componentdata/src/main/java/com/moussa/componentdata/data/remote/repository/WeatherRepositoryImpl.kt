package com.moussa.componentdata.data.remote.repository


import com.moussa.componentdata.data.remote.WeatherPaprikaApi
import com.moussa.componentdata.data.remote.dto.WeatherDetailDto
import com.moussa.componentdata.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherPaprikaApi
) : WeatherRepository {



    override suspend fun getTownWeather(name: String,appId:String): WeatherDetailDto {
        return api.getTownWeather(name,appId)
    }
}