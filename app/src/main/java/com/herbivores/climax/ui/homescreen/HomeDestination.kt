package com.herbivores.climax.ui.homescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.herbivores.climax.repositories.WeatherRepository
import com.herbivores.climax.ui.viewmodels.HomeViewModel

@Composable
fun Home() {
    val viewModel = viewModel { HomeViewModel(WeatherRepository()) }

    val location by viewModel.location.collectAsStateWithLifecycle()
    val locations by viewModel.locations.collectAsStateWithLifecycle()
    val selectingLocation by viewModel.selectingLocation.collectAsStateWithLifecycle()
    val currentWeatherState by viewModel.state.collectAsStateWithLifecycle()
    val currentWeatherExpanded by viewModel.currentWeatherExpanded.collectAsStateWithLifecycle()
    val forecastWeatherState by viewModel.forecastState.collectAsStateWithLifecycle()
    val selectedDay by viewModel.selectedDayWeather.collectAsStateWithLifecycle()

    HomeScreen(
        location = location,
        locations = locations,
        selectingLocation = selectingLocation,
        onToggleSelectingLocation = viewModel::toggleSelectingLocation,
        onLocationSelect = viewModel::updateLocation,
        currentWeatherState = currentWeatherState,
        currentWeatherExpanded = currentWeatherExpanded,
        onToggleCurrentWeatherExpanded = viewModel::toggleCurrentWeatherExpanded,
        forecastWeatherState = forecastWeatherState,
        selectedDay = selectedDay,
        onSelectedDayChange = viewModel::updateSelectedDay,
        onReload = viewModel::reload,
    )
}