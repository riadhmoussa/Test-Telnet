package com.moussa.telnettest.presentation.town_fetaures.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.moussa.telnettest.presentation.components.SubmitButton
import com.moussa.telnettest.ui.theme.AppContentColor
import com.moussa.telnettest.ui.theme.AppThemeColor
import kotlinx.coroutines.flow.collectLatest
import com.moussa.telnettest.R


@Composable
fun AddTownScreen(navController: NavHostController, viewModel: AddTownViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddTownViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddTownViewModel.UiEvent.SaveTown -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.AppThemeColor,
        contentColor = MaterialTheme.colors.AppContentColor,
        topBar = {
        },

        content = {
            Card(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.AppThemeColor
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                ) {
                    OutlinedTextField(
                        value = viewModel.townName.value.text,
                        onValueChange = {
                            viewModel.onEvent(AddTownEvent.EnteredTownName(it))
                        },
                        label = { viewModel.townName.value.hint },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 8.dp)

                    )
                    SubmitButton(stringResource(R.string.save), onClick = {
                        viewModel.onEvent(AddTownEvent.SaveTown)
                    })
                }
            }
        }
    )
}