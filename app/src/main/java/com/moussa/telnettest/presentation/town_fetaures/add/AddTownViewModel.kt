package com.moussa.telnettest.presentation.town_fetaures.add

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moussa.componentdata.domain.model.InvalidNoteException
import com.moussa.componentdata.domain.model.Town
import com.moussa.componentdata.domain.use_case.town.TownUseCases
import com.moussa.telnettest.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddTownViewModel @Inject constructor(
    private val townUseCases: TownUseCases,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _townName = mutableStateOf(
        AddTownState(
            hint = context.getString(R.string.enter_town_name)
        )
    )
    val townName: State<AddTownState> = _townName

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event: AddTownEvent) {
        when (event) {
            is AddTownEvent.EnteredTownName -> {
                _townName.value = townName.value.copy(
                    text = event.value
                )
            }
            is AddTownEvent.SaveTown -> {
                viewModelScope.launch {
                    try {
                        townUseCases.addTown(
                            Town(
                                name = townName.value.text,
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTown)
                    } catch(e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save town"
                            )
                        )
                    }
                }
            }
        }

    }
    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveTown: UiEvent()
    }
}