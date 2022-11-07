package com.moussa.componentdata.data.remote.dto


import Clouds
import Coord
import Main
import Sys
import Weather
import Wind
import com.moussa.componentdata.domain.model.WeatherDetail

data class WeatherDetailDto(
    val coord : Coord,
    val weather : List<Weather>,
    val base : String,
    val main : Main,
    val visibility : Int,
    val wind : Wind,
    val clouds : Clouds,
    val dt : Int,
    val sys : Sys,
    val timezone : Int,
    val id : Int,
    val name : String,
    val cod : Int
)



fun WeatherDetailDto.toWeatherDetail(): WeatherDetail {
    return WeatherDetail(
        coord = coord,
        weather = weather,
        base = base,
        main = main,
        visibility = visibility,
        wind = wind,
        clouds = clouds,
        dt = dt,
        sys = sys,
        timezone = timezone,
        id = id,
        name = name,
        cod = cod,
    )
}