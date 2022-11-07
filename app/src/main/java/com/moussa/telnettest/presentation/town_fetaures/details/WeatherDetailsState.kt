package com.moussa.telnettest.presentation.town_fetaures.details

import com.moussa.componentdata.domain.model.WeatherDetail


data class WeatherDetailsState(
    val isLoading: Boolean = false,
    val watherDetails: WeatherDetail? = null,
    val error: String = ""
)