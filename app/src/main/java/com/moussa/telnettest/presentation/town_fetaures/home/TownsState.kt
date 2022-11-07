package com.moussa.telnettest.presentation.town_fetaures.home

import com.moussa.componentdata.domain.model.Town


data class TownsState(
    val listTowns: List<Town> = emptyList()

)