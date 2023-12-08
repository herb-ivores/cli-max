package com.herbivores.climax.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.herbivores.climax.repositories.WeatherRepository
import com.herbivores.climax.ui.viewmodels.HomeViewModel

//@RootNavGraph(start = true)
//@Destination
@Composable
fun Home() {
    val viewModel = viewModel { HomeViewModel(WeatherRepository()) }

    val currentWeatherState by viewModel.state.collectAsState()
    val forecastWeatherState by viewModel.forecastState.collectAsState()

    HomeScreen(
        currentWeatherState = currentWeatherState,
        forecastWeatherState = forecastWeatherState,
    )
}