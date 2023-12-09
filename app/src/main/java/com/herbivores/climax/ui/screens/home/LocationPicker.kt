package com.herbivores.climax.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowDropDown
import androidx.compose.material.icons.twotone.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.herbivores.climax.models.domain.Location
import com.herbivores.climax.ui.theme.AppTheme
import com.thebrownfoxx.components.HorizontalSpacer
import com.thebrownfoxx.components.extension.Elevation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationPicker(
    location: Location,
    locations: List<Location>,
    selectingLocation: Boolean,
    onSelectingLocationChange: (Boolean) -> Unit,
    onLocationSelect: (Location) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        onClick = { onSelectingLocationChange(!selectingLocation) }
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp),
            ) {
                Icon(imageVector = Icons.TwoTone.LocationOn, contentDescription = "Location icon")
                HorizontalSpacer(width = 16.dp)
                Text(
                    text = location.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f),
                )
                Icon(
                    imageVector = Icons.TwoTone.ArrowDropDown,
                    contentDescription = "Dropdown icon",
                )
            }
            AnimatedVisibility(visible = selectingLocation) {
                Column {
                    for (locationSelection in locations) {
                        Surface(
                            tonalElevation = Elevation.level(2),
                            onClick = { onLocationSelect(locationSelection) },
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(16.dp),
                            ) {
                                HorizontalSpacer(width = (40).dp)
                                Text(
                                    text = locationSelection.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.weight(1f),
                                )
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
fun LocationPickerPreview() {
    AppTheme {
        LocationPicker(
            location = remember { Location.Samples.first() },
            locations = remember { Location.Samples - Location.Samples.first() },
            selectingLocation = false,
            onSelectingLocationChange = {},
            onLocationSelect = {},
        )
    }
}

@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Preview
@Composable
fun LocationPickerExpandedPreview() {
    AppTheme {
        LocationPicker(
            location = remember { Location.Samples.first() },
            locations = remember { Location.Samples - Location.Samples.first() },
            selectingLocation = true,
            onSelectingLocationChange = {},
            onLocationSelect = {},
        )
    }
}