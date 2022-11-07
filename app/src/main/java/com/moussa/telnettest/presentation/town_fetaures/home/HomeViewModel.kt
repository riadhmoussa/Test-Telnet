package com.moussa.telnettest.presentation.town_fetaures.home

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moussa.componentdata.domain.model.InvalidNoteException
import com.moussa.componentdata.domain.model.Town
import com.moussa.componentdata.domain.use_case.town.TownUseCases
import com.moussa.telnettest.R
import com.moussa.telnettest.presentation.town_fetaures.add.AddTownEvent
import com.moussa.telnettest.presentation.town_fetaures.add.AddTownState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val townUseCases: TownUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(TownsState())
    val state: State<TownsState> = _state

    private var getTownsJob: Job? = null


    fun getTowns() {
        getTownsJob?.cancel()
        getTownsJob = townUseCases.getTowns()
            .onEach { towns ->
                _state.value = state.value.copy(
                    listTowns = towns,
                )
            }
            .launchIn(viewModelScope)
    }

}