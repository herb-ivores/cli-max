package com.herbivores.climax.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Air
import androidx.compose.material.icons.twotone.Compress
import androidx.compose.material.icons.twotone.Grain
import androidx.compose.material.icons.twotone.Visibility
import androidx.compose.material.icons.twotone.WaterDrop
import androidx.compose.material.icons.twotone.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.herbivores.climax.models.domain.CurrentWeather
import com.herbivores.climax.models.domain.Direction
import com.herbivores.climax.models.domain.Wind
import com.herbivores.climax.models.domain.celsius
import com.herbivores.climax.models.domain.meters
import com.herbivores.climax.ui.theme.AppTheme
import io.github.fornewid.placeholder.material3.placeholder
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun CurrentWeatherDetails(
    currentWeather: CurrentWeather?,
    modifier: Modifier = Modifier,
) {
    val windSpeed = "${currentWeather?.wind?.speedMetersPerSecond ?: 69} m/s " +
            (currentWeather?.wind?.direction?.symbol ?: "E")
    val sunrise = currentWeather?.sunrise
        ?.format(DateTimeFormatter.ofPattern("h:m a")) ?: "6:09 am"
    val sunset = currentWeather?.sunset
        ?.format(DateTimeFormatter.ofPattern("h:m a")) ?: "6:09 pm"

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        Info(
            icon = Icons.TwoTone.Air,
            iconContentDescription = "Wind icon",
            title = "Wind speed",
            value = windSpeed,
            modifier = Modifier.placeholder(currentWeather == null),
        )
        Info(
            icon = Icons.TwoTone.WaterDrop,
            iconContentDescription = "Humidity icon",
            title = "Humidity",
            value = "${currentWeather?.humidityPercent ?: 69}%",
            modifier = Modifier.placeholder(currentWeather == null),
        )
        Info(
            icon = Icons.TwoTone.Compress,
            iconContentDescription = "Pressure icon",
            title = "Pressure",
            value = "${currentWeather?.pressureMillibars ?: 69} mbar",
            modifier = Modifier.placeholder(currentWeather == null),
        )
        Info(
            icon = Icons.TwoTone.Visibility,
            iconContentDescription = "Visibility icon",
            title = "Visibility",
            value = "${currentWeather?.visibility ?: 69}",
            modifier = Modifier.placeholder(currentWeather == null),
        )
        Info(
            icon = Icons.TwoTone.Grain,
            iconContentDescription = "Precipitation icon",
            title = "Precipitation",
            value = "${currentWeather?.precipitationMillimeters ?: 69} mm",
            modifier = Modifier.placeholder(currentWeather == null),
        )
        Info(
            icon = Icons.TwoTone.WbSunny,
            iconContentDescription = "Sun icon",
            title = "Sunrise, sunset",
            value = "$sunrise, $sunset",
            modifier = Modifier.placeholder(currentWeather == null),
        )
    }
}

@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Preview
@Composable
fun CurrentWeatherDetailsPreview() {
    AppTheme {
        Surface {
            CurrentWeatherDetails(
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
                modifier = Modifier.padding(32.dp),
            )
        }
    }
}

@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Preview
@Composable
fun CurrentWeatherDetailsNullPreview() {
    AppTheme {
        Surface {
            CurrentWeatherDetails(
                currentWeather = null,
                modifier = Modifier.padding(32.dp),
            )
        }
    }
}