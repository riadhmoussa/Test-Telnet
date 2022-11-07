package com.moussa.telnettest.presentation.town_fetaures.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moussa.telnettest.R
import com.moussa.telnettest.presentation.components.ItemData
import com.moussa.telnettest.util.dateFormatter


@Composable
fun TownWeatherDetailsScreen(viewModel: TownWeatherDetailsViewModel = hiltViewModel()) {
    val state = viewModel.stateWeatherDetails.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.watherDetails?.let { watherDetails ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Text(
                        text = "${watherDetails.name} ",
                        style = MaterialTheme.typography.h2,
                    )
                    ItemData(stringResource(R.string.wind_speed),watherDetails.wind.speed.toString())
                    ItemData(stringResource(R.string.sunrise),watherDetails.sys.sunrise.dateFormatter())
                    ItemData(stringResource(R.string.sunset),watherDetails.sys.sunset.dateFormatter())
                }
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}