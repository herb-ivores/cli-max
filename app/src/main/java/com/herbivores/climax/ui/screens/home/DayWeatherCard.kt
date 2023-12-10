package com.herbivores.climax.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.domain.celsius
import com.herbivores.climax.models.domain.DayWeather
import com.herbivores.climax.models.domain.HourWeather
import com.herbivores.climax.ui.theme.AppTheme
import com.thebrownfoxx.components.HorizontalSpacer
import com.thebrownfoxx.components.extension.rememberMutableStateOf
import io.github.fornewid.placeholder.material3.placeholder

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

    Card(
        modifier = modifier,
        onClick = { onClick() },
        // You cannot call dayWeather!!.day here because it can be null when loading,
        // and clicking the loading card will crash the app.
        // Always use ?. over !!. if it wouldn't really make writing the code harder.
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
                HorizontalSpacer(width = 8.dp)
                Text(
                    text = dayWeatherToShow?.day ?: "Cumday",
                    style = typography.titleSmall,
                    modifier = Modifier
                        .placeholder(dayWeatherToShow == null)
                        .weight(1f),
                )
            }
            AnimatedVisibility(visible = expanded) {
                Row(
                    modifier = Modifier
                        .placeholder(dayWeatherToShow == null)
                        .horizontalScroll(rememberScrollState()),
                ) {
                    dayWeather?.hourlyWeather?.forEach { forecast ->
                        Box(modifier = Modifier.padding(6.dp)) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "${forecast.temperature.celsius}°")
                                AsyncImage(
                                    model = forecast.iconUrl,
                                    contentDescription = "weather icon",
                                    modifier = Modifier
                                        .size(24.dp),
                                )
                                Text(text = forecast.time, fontSize = 14.sp)
                            }
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
                day = "Monday",
                hourlyWeather = listOf(),
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
                day = "Monday",
                hourlyWeather = listOf(
                    HourWeather(
                        iconUrl = WeatherApi.getIconUrl("01d"),
                        type = "Clear",
                        temperature = 30.celsius,
                        feelsLike = 32.celsius,
                        time = "7AM",
                    ),
                    HourWeather(
                        iconUrl = WeatherApi.getIconUrl("01d"),
                        type = "Clear",
                        temperature = 30.celsius,
                        feelsLike = 32.celsius,
                        time = "7AM",
                    ),
                    HourWeather(
                        iconUrl = WeatherApi.getIconUrl("01d"),
                        type = "Clear",
                        temperature = 30.celsius,
                        feelsLike = 32.celsius,
                        time = "7AM",
                    ),
                    HourWeather(
                        iconUrl = WeatherApi.getIconUrl("01d"),
                        type = "Clear",
                        temperature = 30.celsius,
                        feelsLike = 32.celsius,
                        time = "7AM",
                    ),
                    HourWeather(
                        iconUrl = WeatherApi.getIconUrl("01d"),
                        type = "Clear",
                        temperature = 30.celsius,
                        feelsLike = 32.celsius,
                        time = "7AM",
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