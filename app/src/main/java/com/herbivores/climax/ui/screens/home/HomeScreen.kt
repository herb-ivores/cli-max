package com.herbivores.climax.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.herbivores.climax.apiclient.ApiState
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.celsius
import com.herbivores.climax.models.domain.CurrentWeather
import com.herbivores.climax.models.domain.DayWeather
import com.thebrownfoxx.components.VerticalSpacer

@Composable
fun HomeScreen(
    currentWeatherState: ApiState<CurrentWeather>,
    dayWeathersState: ApiState<List<DayWeather>>,
    modifier: Modifier = Modifier,
) {
    if (currentWeatherState is ApiState.Success && dayWeathersState is ApiState.Success) {
        val currentWeather = currentWeatherState.data
        val dayWeathers = dayWeathersState.data
        
        Scaffold(modifier = modifier) { contentPadding ->
            LazyColumn(contentPadding = contentPadding) {
                item {
                    CurrentWeatherHeader(
                        currentWeather = currentWeather,
                        modifier = Modifier.fillParentMaxHeight(0.6f),
                    )
                }
                items(dayWeathers) { dayWeather ->
                    DayWeatherCard(
                        dayWeather = dayWeather,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    )
                }
                item { VerticalSpacer(height = 8.dp) }
            }
        }
    }
}

@PreviewScreenSizes
@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        currentWeatherState = ApiState.Success(
            CurrentWeather(
                location = "Angeles",
                iconUrl = "${WeatherApi.IMAGE_BASE_URL}01d@2x.png",
                type = "Clear",
                day = "Monday",
                time = "12:00 PM",
                temperature = 30.celsius,
                feelsLike = 32.celsius,
            )
        ),
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