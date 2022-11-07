package com.moussa.telnettest.presentation.town_fetaures.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moussa.componentdata.common.Resource
import com.moussa.componentdata.domain.use_case.get_town_weather.GetTownWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class TownWeatherDetailsViewModel @Inject constructor(
    private val getTownWeatherUseCase: GetTownWeatherUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {



    private val _stateWeatherDetails = mutableStateOf(WeatherDetailsState())
    val stateWeatherDetails: State<WeatherDetailsState> = _stateWeatherDetails



    init {
        savedStateHandle.get<String>("name")?.let { name ->
            if(name.isNotEmpty()) {
                getTownWeather(name.trim())
            }
        }

    }

    private fun getTownWeather(name: String) {
        getTownWeatherUseCase(name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateWeatherDetails.value = WeatherDetailsState(watherDetails = result.data)
                }
                is Resource.Error -> {
                    _stateWeatherDetails.value = WeatherDetailsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateWeatherDetails.value = WeatherDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}