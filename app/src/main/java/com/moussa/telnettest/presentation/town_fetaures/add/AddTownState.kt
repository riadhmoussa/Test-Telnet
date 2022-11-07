package com.moussa.telnettest.presentation.town_fetaures.add


data class AddTownState(
    val text: String = "",
    val hint: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
)
