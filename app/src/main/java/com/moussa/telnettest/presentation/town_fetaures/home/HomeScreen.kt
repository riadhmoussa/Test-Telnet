package com.moussa.telnettest.presentation.town_fetaures.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.moussa.telnettest.R
import com.moussa.telnettest.presentation.components.TownItem
import com.moussa.telnettest.presentation.navigation.Screen
import com.moussa.telnettest.ui.theme.AppContentColor
import com.moussa.telnettest.ui.theme.AppThemeColor

@Composable
fun HomeScreen(navController: NavHostController,
               viewModel: HomeViewModel = hiltViewModel(),lifecycleOwner: LifecycleOwner= LocalLifecycleOwner.current) {

    val state = viewModel.state.value
    DisposableEffect(lifecycleOwner){
        val observer = LifecycleEventObserver{source, event ->
            if(event==Lifecycle.Event.ON_RESUME){
               viewModel.getTowns()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    Scaffold(
        backgroundColor = MaterialTheme.colors.AppThemeColor,
        contentColor = MaterialTheme.colors.AppContentColor,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddTown.route)},
                backgroundColor = Color.Red,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        },
        content = {
            if(state.listTowns.isEmpty()){
                Text(text = stringResource(R.string.no_data), fontSize = 30.sp,
                    textAlign = TextAlign.Center)
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.listTowns.size) { index ->
                    TownItem(
                        town = state.listTowns[index],
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.TownWeatherDetails.route +
                                            "?name=${state.listTowns[index].name}"
                                )
                            }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}

