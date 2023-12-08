package com.herbivores.climax.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.herbivores.climax.apiclient.ApiState
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.CurrentWeather

@Composable
fun HomeScreen(
    currentWeatherState: ApiState<CurrentWeather>,
    modifier: Modifier = Modifier,
) {
    if (currentWeatherState is ApiState.Success) {
        val currentWeather = currentWeatherState.data
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize(),
        ) {
            Text(text = currentWeather.location)
            AsyncImage(
                model = "${WeatherApi.IMAGE_BASE_URL}${currentWeather.iconName}@2x.png",
                contentDescription = null,
                modifier = Modifier.size(48.dp),
            )
            Text(text = currentWeather.day)
            Text(text = currentWeather.time)
            Text(text = currentWeather.temperature)
            Text(text = "Feels like ${currentWeather.feelsLike}")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        currentWeatherState = ApiState.Success(
            CurrentWeather(
                location = "Angeles",
                iconName = "01d",
                type = "Clear",
                day = "Monday",
                time = "12:00 PM",
                temperature = "30 °C",
                feelsLike = "32 °C",
            )
        ),
    )
}