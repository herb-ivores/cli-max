package com.herbivores.climax.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.herbivores.climax.models.celsius
import com.herbivores.climax.models.domain.CurrentWeather
import com.herbivores.climax.ui.theme.AppTheme
import com.thebrownfoxx.components.HorizontalSpacer

@Composable
fun CurrentWeatherHeader(
    currentWeather: CurrentWeather,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(
            start = 32.dp,
            top = 64.dp,
            end = 16.dp,
            bottom = 64.dp,
        ),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.TwoTone.LocationOn, contentDescription = "Location icon")
                HorizontalSpacer(width = 8.dp)
                Text(text = currentWeather.location, style = typography.titleLarge)
            }
            Text(
                text = "${currentWeather.temperature.celsius}°",
                style = typography.displayLarge,
            )
            Text(text = currentWeather.type, style = typography.titleLarge)
            Text(
                text = "Feels like ${currentWeather.feelsLike.celsius}°",
                style = typography.labelLarge,
            )
        }
        AsyncImage(
            model = currentWeather.iconUrl,
            contentDescription = "Weather icon",
            modifier = Modifier.size(128.dp),
        )
    }
}

@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Preview
@Composable
fun CurrentWeatherHeaderPreview() {
    AppTheme {
        Surface {
            CurrentWeatherHeader(
                currentWeather = CurrentWeather(
                    location = "Angeles",
                    iconUrl = "01d",
                    type = "Clear",
                    day = "Monday",
                    time = "12:00 PM",
                    temperature = 30.celsius,
                    feelsLike = 32.celsius,
                )
            )
        }
    }
}