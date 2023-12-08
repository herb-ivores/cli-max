package com.herbivores.climax.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.herbivores.climax.apiclient.ApiState
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.celsius
import com.herbivores.climax.models.domain.DayWeather
import com.herbivores.climax.repositories.WeatherRepository
import com.herbivores.climax.ui.viewmodels.HomeViewModel

//@RootNavGraph(start = true)
//@Destination
@Composable
fun Home() {
    val viewModel = viewModel { HomeViewModel(WeatherRepository()) }

    val currentWeatherState by viewModel.state.collectAsState()
    HomeScreen(
        currentWeatherState = currentWeatherState,
        // Temporary hardcoded values
        dayWeathersState = ApiState.Success(
            listOf(
                DayWeather(
                    day = "Wednesday",
                    iconUrl = "${WeatherApi.IMAGE_BASE_URL}01d@2x.png",
                    type = "Clear",
                    temperature = 30.celsius,
                    feelsLike = 32.celsius,
                ),
                DayWeather(
                    day = "Thursday",
                    iconUrl = "${WeatherApi.IMAGE_BASE_URL}01d@2x.png",
                    type = "Clear",
                    temperature = 30.celsius,
                    feelsLike = 32.celsius,
                ),
                DayWeather(
                    day = "Friday",
                    iconUrl = "${WeatherApi.IMAGE_BASE_URL}01d@2x.png",
                    type = "Clear",
                    temperature = 30.celsius,
                    feelsLike = 32.celsius,
                ),
                DayWeather(
                    day = "Saturday",
                    iconUrl = "${WeatherApi.IMAGE_BASE_URL}01d@2x.png",
                    type = "Clear",
                    temperature = 30.celsius,
                    feelsLike = 32.celsius,
                ),
                DayWeather(
                    day = "Sunday",
                    iconUrl = "${WeatherApi.IMAGE_BASE_URL}01d@2x.png",
                    type = "Clear",
                    temperature = 30.celsius,
                    feelsLike = 32.celsius,
                ),
            )
        ),
    )
}