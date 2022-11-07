package com.moussa.telnettest.presentation.town_fetaures.add


sealed class AddTownEvent{
    data class EnteredTownName(val value: String): AddTownEvent()
    object SaveTown: AddTownEvent()
}