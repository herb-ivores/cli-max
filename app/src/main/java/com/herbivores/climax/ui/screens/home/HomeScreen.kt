package com.herbivores.climax.ui.screens.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.herbivores.climax.apiclient.ApiState
import com.herbivores.climax.apiclient.ApiState.Loading
import com.herbivores.climax.apiclient.ApiState.Success
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.domain.celsius
import com.herbivores.climax.models.domain.CurrentWeather
import com.herbivores.climax.models.domain.DayWeather
import com.herbivores.climax.models.domain.ForecastWeather
import com.herbivores.climax.models.domain.HourWeather
import com.herbivores.climax.models.domain.Location
import com.herbivores.climax.ui.theme.AppTheme
import com.thebrownfoxx.components.extension.Elevation
import com.thebrownfoxx.components.extension.minus
import com.thebrownfoxx.components.extension.plus

@Composable
fun HomeScreen(
    location: Location,
    locations: List<Location>,
    selectingLocation: Boolean,
    onSelectingLocationChange: (Boolean) -> Unit,
    onLocationSelect: (Location) -> Unit,
    currentWeatherState: ApiState<CurrentWeather>,
    forecastWeatherState: ApiState<ForecastWeather>,
    selectedDay: DayWeather?,
    onSelectedDayChange: (DayWeather) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val scrolled by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0 ||
                    lazyListState.firstVisibleItemScrollOffset > 0
        }
    }
    val elevation by animateDpAsState(
        targetValue = if (scrolled) Elevation.level(3) else 0.dp,
        label = "",
    )
    
    Scaffold(
        modifier = modifier,
        topBar = {
            Surface(tonalElevation = elevation) {
                Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
                    LocationPicker(
                        location = location,
                        locations = locations,
                        selectingLocation = selectingLocation,
                        onSelectingLocationChange = onSelectingLocationChange,
                        onLocationSelect = onLocationSelect,
                        modifier = Modifier.padding(16.dp),
                    )
                    if (currentWeatherState is Success || currentWeatherState is Loading) {
                        val currentWeather =
                            if (currentWeatherState is Success) currentWeatherState.data else null

                        CurrentWeatherHeader(
                            currentWeather = currentWeather,
                            modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 32.dp),
                        )
                    }
                }
            }
        }
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding + PaddingValues(16.dp) - PaddingValues(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = lazyListState,
        ) {
            if (forecastWeatherState is Success) {
                items(items = forecastWeatherState.data.dailyWeather) { dayWeather ->
                    DayWeatherCard(
                        dayWeather = dayWeather,
                        expanded = selectedDay == dayWeather,
                        onClick = { onSelectedDayChange(dayWeather) },
                    )
                }
            } else if (forecastWeatherState is Loading) {
                items(5) {
                    DayWeatherCard(
                        dayWeather = null,
                        expanded = false,
                        onClick = {},
                    )
                }
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
    AppTheme {
        HomeScreen(
            location = remember { Location.Samples.first() },
            locations = remember { Location.Samples - Location.Samples.first() },
            selectingLocation = false,
            onSelectingLocationChange = {},
            onLocationSelect = {},
            currentWeatherState = Success(
                CurrentWeather(
                    location = "Angeles",
                    iconUrl = WeatherApi.getIconUrl("01d"),
                    type = "Clear",
                    day = "Monday",
                    time = "12:00 PM",
                    temperature = 30.celsius,
                    feelsLike = 32.celsius,
                )
            ),
            forecastWeatherState = Success(
                ForecastWeather(
                    location = "Angeles",
                    dailyWeather = listOf(
                        DayWeather(
                            day = "Monday",
                            hourlyWeather = listOf(
                                HourWeather(
                                    iconUrl = WeatherApi.getIconUrl("01d"),
                                    type = "Clear",
                                    temperature = 30.celsius,
                                    feelsLike = 32.celsius,
                                    time = "7AM"
                                )
                            ),
                        ),
                        DayWeather(
                            day = "Monday",
                            hourlyWeather = listOf(
                                HourWeather(
                                    iconUrl = WeatherApi.getIconUrl("01d"),
                                    type = "Clear",
                                    temperature = 30.celsius,
                                    feelsLike = 32.celsius,
                                    time = "7AM"
                                )
                            ),
                        ),
                        DayWeather(
                            day = "Monday",
                            hourlyWeather = listOf(
                                HourWeather(
                                    iconUrl = WeatherApi.getIconUrl("01d"),
                                    type = "Clear",
                                    temperature = 30.celsius,
                                    feelsLike = 32.celsius,
                                    time = "7AM"
                                )
                            ),
                        ),
                        DayWeather(
                            day = "Monday",
                            hourlyWeather = listOf(
                                HourWeather(
                                    iconUrl = WeatherApi.getIconUrl("01d"),
                                    type = "Clear",
                                    temperature = 30.celsius,
                                    feelsLike = 32.celsius,
                                    time = "7AM"
                                )
                            ),
                        ),
                        DayWeather(
                            day = "Monday",
                            hourlyWeather = listOf(
                                HourWeather(
                                    iconUrl = WeatherApi.getIconUrl("01d"),
                                    type = "Clear",
                                    temperature = 30.celsius,
                                    feelsLike = 32.celsius,
                                    time = "7AM"
                                )
                            ),
                        ),
                    ),
                ),
            ),
            selectedDay = null,
            onSelectedDayChange = {},
        )
    }
}