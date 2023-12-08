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

@Composable
fun DayWeatherCard(
    dayWeather: DayWeather,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
) {
    Card(modifier = modifier.animateContentSize()) {
        Crossfade(targetState = expanded, label = "") { expanded ->
            if (!expanded) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp),
                ) {
                    AsyncImage(
                        model = dayWeather.iconUrl,
                        contentDescription = "Weather icon",
                        modifier = Modifier.size(24.dp),
                    )
                    HorizontalSpacer(width = 8.dp)
                    Text(
                        text = dayWeather.day,
                        style = typography.titleSmall,
                        modifier = Modifier.weight(1f),
                    )
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "${dayWeather.temperature.celsius}°",
                            textAlign = TextAlign.End,
                            style = typography.bodySmall,
                        )
                        Text(
                            text = "Feels like ${dayWeather.feelsLike.celsius}°",
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
                iconUrl = "${WeatherApi.IMAGE_BASE_URL}01d@2x.png",
                type = "Clear",
                temperature = 30.celsius,
                feelsLike = 32.celsius,
            ),
            modifier = Modifier.padding(16.dp),
        )
    }
}