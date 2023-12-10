package com.herbivores.climax.ui.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.herbivores.climax.apiclient.ApiState
import com.herbivores.climax.apiclient.ApiState.Failure
import com.herbivores.climax.apiclient.ApiState.Loading
import com.herbivores.climax.apiclient.ApiState.Success
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.domain.CurrentWeather
import com.herbivores.climax.models.domain.DayWeather
import com.herbivores.climax.models.domain.Direction
import com.herbivores.climax.models.domain.ForecastWeather
import com.herbivores.climax.models.domain.HourWeather
import com.herbivores.climax.models.domain.Location
import com.herbivores.climax.models.domain.Temperature
import com.herbivores.climax.models.domain.Wind
import com.herbivores.climax.models.domain.celsius
import com.herbivores.climax.models.domain.meters
import com.herbivores.climax.ui.theme.AppTheme
import com.thebrownfoxx.components.extension.Elevation
import com.thebrownfoxx.components.extension.minus
import com.thebrownfoxx.components.extension.plus
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

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
    onReload: () -> Unit,
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.statusBars)
                        .animateContentSize(),
                ) {
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
        if (currentWeatherState is Failure || forecastWeatherState is Failure) {
            ErrorLoading(
                onReload = onReload,
                modifier = Modifier
                    .padding(contentPadding + PaddingValues(32.dp))
                    .fillMaxSize(),
            )
        } else {
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
                    iconUrl = "01d",
                    type = "Clear",
                    dateTime = LocalDateTime.now(),
                    temperature = 30.celsius,
                    feelsLike = 32.celsius,
                    wind = Wind(
                        speedMetersPerSecond = 69.0,
                        direction = Direction(degrees = 69),
                    ),
                    humidityPercent = 69,
                    pressureMillibars = 420,
                    visibility = 69.meters,
                    precipitationMillimeters = 69.69,
                    sunrise = LocalTime.now(),
                    sunset = LocalTime.now(),
                ),
            ),
            forecastWeatherState = Success(
                ForecastWeather(
                    location = "Angeles",
                    dailyWeather = listOf(
                        DayWeather(
                            date = LocalDate.now(),
                            humidity = 25.0,
                            maxFeelsLike = Temperature(26.0),
                            maxTemperature = Temperature(27.0),
                            sunset = LocalTime.MIDNIGHT,
                            sunrise = LocalTime.MIDNIGHT,
                            wind = Wind(30.0,Direction.EAST),
                            hourlyWeather = listOf(
                                HourWeather(
                                    iconUrl = WeatherApi.getIconUrl("01d"),
                                    type = "Clear",
                                    temperature = 30.celsius,
                                    feelsLike = 32.celsius,
                                    time = LocalTime.MIDNIGHT,
                                )
                            ),
                        ),
                        DayWeather(
                            date = LocalDate.now(),
                            humidity = 25.0,
                            maxFeelsLike = Temperature(26.0),
                            maxTemperature = Temperature(27.0),
                            sunset = LocalTime.MIDNIGHT,
                            sunrise = LocalTime.MIDNIGHT,
                            wind = Wind(30.0,Direction.EAST),
                            hourlyWeather = listOf(
                                HourWeather(
                                    iconUrl = WeatherApi.getIconUrl("01d"),
                                    type = "Clear",
                                    temperature = 30.celsius,
                                    feelsLike = 32.celsius,
                                    time = LocalTime.MIDNIGHT,
                                )
                            ),
                        ),
                        DayWeather(
                            date = LocalDate.now(),
                            humidity = 25.0,
                            maxFeelsLike = Temperature(26.0),
                            maxTemperature = Temperature(27.0),
                            sunset = LocalTime.MIDNIGHT,
                            sunrise = LocalTime.MIDNIGHT,
                            wind = Wind(30.0,Direction.EAST),
                            hourlyWeather = listOf(
                                HourWeather(
                                    iconUrl = WeatherApi.getIconUrl("01d"),
                                    type = "Clear",
                                    temperature = 30.celsius,
                                    feelsLike = 32.celsius,
                                    time = LocalTime.MIDNIGHT,
                                )
                            ),
                        ),
                        DayWeather(
                            date = LocalDate.now(),
                            humidity = 25.0,
                            maxFeelsLike = Temperature(26.0),
                            maxTemperature = Temperature(27.0),
                            sunset = LocalTime.MIDNIGHT,
                            sunrise = LocalTime.MIDNIGHT,
                            wind = Wind(30.0,Direction.EAST),
                            hourlyWeather = listOf(
                                HourWeather(
                                    iconUrl = WeatherApi.getIconUrl("01d"),
                                    type = "Clear",
                                    temperature = 30.celsius,
                                    feelsLike = 32.celsius,
                                    time = LocalTime.MIDNIGHT,
                                )
                            ),
                        ),
                        DayWeather(
                            date = LocalDate.now(),
                            humidity = 25.0,
                            maxFeelsLike = Temperature(26.0),
                            maxTemperature = Temperature(27.0),
                            sunset = LocalTime.MIDNIGHT,
                            sunrise = LocalTime.MIDNIGHT,
                            wind = Wind(30.0,Direction.EAST),
                            hourlyWeather = listOf(
                                HourWeather(
                                    iconUrl = WeatherApi.getIconUrl("01d"),
                                    type = "Clear",
                                    temperature = 30.celsius,
                                    feelsLike = 32.celsius,
                                    time = LocalTime.MIDNIGHT,
                                )
                            ),
                        ),
                    ),
                ),
            ),
            selectedDay = null,
            onSelectedDayChange = {},
            onReload = {},
        )
    }
}