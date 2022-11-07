package com.moussa.componentdata.domain.model

import Clouds
import Coord
import Main
import Sys
import Weather
import Wind

data class WeatherDetail(
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
