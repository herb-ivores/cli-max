package com.herbivores.climax.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.herbivores.climax.models.domain.celsius
import com.herbivores.climax.models.domain.CurrentWeather
import com.herbivores.climax.models.domain.Direction
import com.herbivores.climax.models.domain.Wind
import com.herbivores.climax.models.domain.meters
import com.herbivores.climax.ui.theme.AppTheme
import com.thebrownfoxx.components.extension.rememberMutableStateOf
import io.github.fornewid.placeholder.material3.placeholder
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun CurrentWeatherHeader(
    currentWeather: CurrentWeather?,
    modifier: Modifier = Modifier,
) {
    var previousCurrentWeather by rememberMutableStateOf<CurrentWeather?>(null)

    LaunchedEffect(currentWeather) {
        if (currentWeather != null) {
            previousCurrentWeather = currentWeather
        }
    }

    val currentWeatherToShow = currentWeather ?: previousCurrentWeather

    Row(modifier = modifier) {
        Column(
            modifier = Modifier
                .weight(1f)
                .placeholder(visible = currentWeather == null),
        ) {
            Text(
                text = "${currentWeatherToShow?.temperature?.celsius ?: 69}°",
                style = typography.displayLarge,
            )
            Text(
                text = currentWeatherToShow?.type ?: "Cum rain",
                style = typography.titleLarge,
            )
            Text(
                text = "Feels like ${currentWeatherToShow?.feelsLike?.celsius ?: 69}°",
                style = typography.labelLarge,
            )
        }
        AsyncImage(
            model = currentWeatherToShow?.iconUrl,
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
                modifier = Modifier.padding(32.dp)
            )
        }
    }
}

@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Preview
@Composable
fun CurrentWeatherHeaderNullPreview() {
    AppTheme {
        Surface {
            CurrentWeatherHeader(
                currentWeather = null,
                modifier = Modifier.padding(32.dp)
            )
        }
    }
}