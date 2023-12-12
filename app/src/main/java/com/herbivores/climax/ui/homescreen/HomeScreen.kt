package com.herbivores.climax.ui.homescreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.herbivores.climax.apiclient.ApiState
import com.herbivores.climax.apiclient.ApiState.Success
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.domain.Direction
import com.herbivores.climax.models.domain.Location
import com.herbivores.climax.models.domain.Temperature
import com.herbivores.climax.models.domain.Wind
import com.herbivores.climax.models.domain.celsius
import com.herbivores.climax.models.domain.current.CurrentWeather
import com.herbivores.climax.models.domain.forecast.DayWeather
import com.herbivores.climax.models.domain.forecast.ForecastWeather
import com.herbivores.climax.models.domain.forecast.HourWeather
import com.herbivores.climax.models.domain.meters
import com.herbivores.climax.ui.theme.AppTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    location: Location,
    locations: List<Location>,
    selectingLocation: Boolean,
    onToggleSelectingLocation: () -> Unit,
    onLocationSelect: (Location) -> Unit,
    currentWeatherState: ApiState<CurrentWeather>,
    currentWeatherExpanded: Boolean,
    onToggleCurrentWeatherExpanded: () -> Unit,
    forecastWeatherState: ApiState<ForecastWeather>,
    selectedDay: DayWeather?,
    onSelectedDayChange: (DayWeather) -> Unit,
    onReload: () -> Unit,
    modifier: Modifier = Modifier,
    windowWidthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
) {
    if (windowWidthSizeClass == WindowWidthSizeClass.Compact) {
        VerticalHomeScreen(
            location = location,
            locations = locations,
            selectingLocation = selectingLocation,
            onToggleSelectingLocation = onToggleSelectingLocation,
            onLocationSelect = onLocationSelect,
            currentWeatherState = currentWeatherState,
            currentWeatherExpanded = currentWeatherExpanded,
            onToggleCurrentWeatherExpanded = onToggleCurrentWeatherExpanded,
            forecastWeatherState = forecastWeatherState,
            selectedDay = selectedDay,
            onSelectedDayChange = onSelectedDayChange,
            onReload = onReload,
            modifier = modifier,
        )
    } else {
        HorizontalHomeScreen(
            location = location,
            locations = locations,
            selectingLocation = selectingLocation,
            onToggleSelectingLocation = onToggleSelectingLocation,
            onLocationSelect = onLocationSelect,
            currentWeatherState = currentWeatherState,
            currentWeatherExpanded = currentWeatherExpanded,
            onToggleCurrentWeatherExpanded = onToggleCurrentWeatherExpanded,
            forecastWeatherState = forecastWeatherState,
            selectedDay = selectedDay,
            onSelectedDayChange = onSelectedDayChange,
            onReload = onReload,
            modifier = modifier,
        )
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
            onToggleSelectingLocation = {},
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
            currentWeatherExpanded = false,
            onToggleCurrentWeatherExpanded = {},
            forecastWeatherState = Success(
                ForecastWeather(
                    location = "Angeles",
                    dailyWeather = listOf(
                        DayWeather(
                            date = LocalDate.now(),
                            humidity = 25,
                            maxFeelsLike = Temperature(26.0),
                            maxTemperature = Temperature(27.0),
                            sunset = LocalTime.MIDNIGHT,
                            sunrise = LocalTime.MIDNIGHT,
                            wind = Wind(30.0,Direction.EAST),
                            iconUrl = WeatherApi.getIconUrl("01d"),
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
                            humidity = 25,
                            maxFeelsLike = Temperature(26.0),
                            maxTemperature = Temperature(27.0),
                            sunset = LocalTime.MIDNIGHT,
                            sunrise = LocalTime.MIDNIGHT,
                            wind = Wind(30.0,Direction.EAST),
                            iconUrl = WeatherApi.getIconUrl("01d"),
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
                            humidity = 25,
                            maxFeelsLike = Temperature(26.0),
                            maxTemperature = Temperature(27.0),
                            sunset = LocalTime.MIDNIGHT,
                            sunrise = LocalTime.MIDNIGHT,
                            wind = Wind(30.0,Direction.EAST),
                            iconUrl = WeatherApi.getIconUrl("01d"),
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
                            humidity = 25,
                            maxFeelsLike = Temperature(26.0),
                            maxTemperature = Temperature(27.0),
                            sunset = LocalTime.MIDNIGHT,
                            sunrise = LocalTime.MIDNIGHT,
                            wind = Wind(30.0,Direction.EAST),
                            iconUrl = WeatherApi.getIconUrl("01d"),
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
                            humidity = 25,
                            maxFeelsLike = Temperature(26.0),
                            maxTemperature = Temperature(27.0),
                            sunset = LocalTime.MIDNIGHT,
                            sunrise = LocalTime.MIDNIGHT,
                            wind = Wind(30.0,Direction.EAST),
                            iconUrl = WeatherApi.getIconUrl("01d"),
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