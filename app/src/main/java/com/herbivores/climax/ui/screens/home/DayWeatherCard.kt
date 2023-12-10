package com.herbivores.climax.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.domain.DayWeather
import com.herbivores.climax.models.domain.Direction
import com.herbivores.climax.models.domain.HourWeather
import com.herbivores.climax.models.domain.Temperature
import com.herbivores.climax.models.domain.Wind
import com.herbivores.climax.models.domain.celsius
import com.herbivores.climax.ui.theme.AppTheme
import com.thebrownfoxx.components.HorizontalSpacer
import com.thebrownfoxx.components.extension.minus
import com.thebrownfoxx.components.extension.rememberMutableStateOf
import io.github.fornewid.placeholder.material3.placeholder
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayWeatherCard(
    dayWeather: DayWeather?,
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var previousDayWeather by rememberMutableStateOf<DayWeather?>(null)

    LaunchedEffect(dayWeather) {
        if (dayWeather != null) {
            previousDayWeather = dayWeather
        }
    }

    val dayWeatherToShow = dayWeather ?: previousDayWeather
    val day = dayWeatherToShow?.date?.dayOfWeek?.name
        ?.lowercase()
        ?.replaceFirstChar { it.uppercase() }
        ?: "Cumday"

    Card(
        modifier = modifier,
        onClick = { onClick() },
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp),
            ) {
                AsyncImage(
                    model = dayWeatherToShow?.hourlyWeather?.firstOrNull()?.iconUrl,
                    contentDescription = "Weather icon",
                    modifier = Modifier
                        .placeholder(dayWeatherToShow == null)
                        .size(24.dp),
                )
                HorizontalSpacer(width = 16.dp)
                Text(
                    text = day,
                    style = typography.titleSmall,
                    modifier = Modifier
                        .placeholder(dayWeatherToShow == null)
                        .weight(1f),
                )
            }
            AnimatedVisibility(visible = expanded) {
                LazyRow(
                    contentPadding = PaddingValues(16.dp) - PaddingValues(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .placeholder(dayWeatherToShow == null),
                ) {
                    items(dayWeather?.hourlyWeather ?: emptyList()) { hourWeather ->
                        val time = hourWeather.time
                            .format(DateTimeFormatter.ofPattern("h a"))

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "${hourWeather.temperature.celsius}°")
                            AsyncImage(
                                model = hourWeather.iconUrl,
                                contentDescription = "Weather icon",
                                modifier = Modifier.size(24.dp),
                            )
                            Text(text = time, style = typography.labelSmall)
                        }
                    }
                }
            }
        }
    }
}

@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Preview
@Composable
fun DayWeatherCardPreview() {
    AppTheme {
        DayWeatherCard(
            dayWeather = DayWeather(
                date = LocalDate.now(),
                hourlyWeather = listOf(),
                humidity = 25.0,
                maxFeelsLike = Temperature(26.0),
                maxTemperature = Temperature(27.0),
                sunset = LocalTime.MIDNIGHT,
                sunrise = LocalTime.MIDNIGHT,
                wind = Wind(30.0, Direction.EAST)
            ),
            expanded = false,
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}

@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Preview
@Composable
fun DayWeatherCardExpandedPreview() {
    AppTheme {
        DayWeatherCard(
            dayWeather = DayWeather(
                date = LocalDate.now(),
                humidity = 25.0,
                maxFeelsLike = Temperature(26.0),
                maxTemperature = Temperature(27.0),
                sunset = LocalTime.MIDNIGHT,
                sunrise = LocalTime.MIDNIGHT,
                wind = Wind(30.0, Direction.EAST),
                hourlyWeather = listOf(
                    HourWeather(
                        iconUrl = WeatherApi.getIconUrl("01d"),
                        type = "Clear",
                        temperature = 30.celsius,
                        feelsLike = 32.celsius,
                        time = LocalTime.MIDNIGHT,
                    ),
                    HourWeather(
                        iconUrl = WeatherApi.getIconUrl("01d"),
                        type = "Clear",
                        temperature = 30.celsius,
                        feelsLike = 32.celsius,
                        time = LocalTime.MIDNIGHT,
                    ),
                    HourWeather(
                        iconUrl = WeatherApi.getIconUrl("01d"),
                        type = "Clear",
                        temperature = 30.celsius,
                        feelsLike = 32.celsius,
                        time = LocalTime.MIDNIGHT,
                    ),
                    HourWeather(
                        iconUrl = WeatherApi.getIconUrl("01d"),
                        type = "Clear",
                        temperature = 30.celsius,
                        feelsLike = 32.celsius,
                        time = LocalTime.MIDNIGHT,
                    ),
                    HourWeather(
                        iconUrl = WeatherApi.getIconUrl("01d"),
                        type = "Clear",
                        temperature = 30.celsius,
                        feelsLike = 32.celsius,
                        time = LocalTime.MIDNIGHT,
                    ),
                ),
            ),
            expanded = true,
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}

@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Preview
@Composable
fun DayWeatherCardNullPreview() {
    AppTheme {
        DayWeatherCard(
            dayWeather = null,
            onClick = {},
            expanded = false,
            modifier = Modifier.padding(16.dp),
        )
    }
}