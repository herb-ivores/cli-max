package com.herbivores.climax.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.herbivores.climax.repositories.WeatherRepository
import com.herbivores.climax.ui.viewmodels.HomeViewModel

//@RootNavGraph(start = true)
//@Destination
@Composable
fun Home() {
    val viewModel = viewModel { HomeViewModel(WeatherRepository()) }

    val location by viewModel.location.collectAsStateWithLifecycle()
    val locations by viewModel.locations.collectAsStateWithLifecycle()
    val selectingLocation by viewModel.selectingLocation.collectAsStateWithLifecycle()
    val currentWeatherState by viewModel.state.collectAsStateWithLifecycle()
    val forecastWeatherState by viewModel.forecastState.collectAsStateWithLifecycle()

    HomeScreen(
        location = location,
        locations = locations,
        selectingLocation = selectingLocation,
        onSelectingLocationChange = viewModel::updateSelectingLocation,
        onLocationSelect = viewModel::updateLocation,
        currentWeatherState = currentWeatherState,
        forecastWeatherState = forecastWeatherState,
    )
}