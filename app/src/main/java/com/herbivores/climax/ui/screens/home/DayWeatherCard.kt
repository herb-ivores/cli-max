package com.herbivores.climax.ui.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.celsius
import com.herbivores.climax.models.domain.DayWeather
import com.herbivores.climax.ui.theme.AppTheme
import com.thebrownfoxx.components.HorizontalSpacer
import com.thebrownfoxx.components.extension.rememberMutableStateOf
import io.github.fornewid.placeholder.material3.placeholder

@Composable
fun DayWeatherCard(
    dayWeather: DayWeather?,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
) {
    var previousDayWeather by rememberMutableStateOf<DayWeather?>(null)

    LaunchedEffect(dayWeather) {
        if (dayWeather != null) {
            previousDayWeather = dayWeather
        }
    }

    val dayWeatherToShow = dayWeather ?: previousDayWeather
    
    Card(modifier = modifier.animateContentSize()) {
        Crossfade(targetState = expanded, label = "") { expanded ->
            if (!expanded) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp),
                ) {
                    AsyncImage(
                        model = dayWeatherToShow?.iconUrl,
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
                    HorizontalSpacer(width = 32.dp)
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.placeholder(dayWeatherToShow == null),
                    ) {
                        Text(
                            text = "${dayWeatherToShow?.temperature?.celsius ?: 69}°",
                            textAlign = TextAlign.End,
                            style = typography.bodySmall,
                        )
                        Text(
                            text = "Feels like ${dayWeatherToShow?.feelsLike?.celsius ?: 69}°",
                            textAlign = TextAlign.End,
                            style = typography.labelSmall,
                        )
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
                iconUrl = WeatherApi.getIconUrl("01d"),
                type = "Clear",
                temperature = 30.celsius,
                feelsLike = 32.celsius,
            ),
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
            modifier = Modifier.padding(16.dp),
        )
    }
}