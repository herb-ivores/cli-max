package com.herbivores.climax.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Air
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp
import com.herbivores.climax.ui.theme.AppTheme
import com.thebrownfoxx.components.HorizontalSpacer

@Composable
fun Info(
    icon: ImageVector,
    iconContentDescription: String,
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    small: Boolean = false,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Icon(imageVector = icon, contentDescription = iconContentDescription)
        HorizontalSpacer(width = 16.dp)
        Column {
            Text(
                text = title.uppercase(),
                maxLines = 1,
                style = if (small) typography.labelSmall else typography.labelMedium,
            )
            Text(
                text = value,
                maxLines = 1,
                style = if (small) typography.bodySmall else typography.bodyMedium,
            )
        }
    }
}

@PreviewFontScale
@Preview
@Composable
fun InfoPreview() {
    AppTheme {
        Info(
            icon = Icons.TwoTone.Air,
            iconContentDescription = "Wind icon",
            title = "Wind speed",
            value = "69 m/s",
            modifier = Modifier.padding(16.dp),
        )
    }
}

@PreviewFontScale
@Preview
@Composable
fun InfoSmallPreview() {
    AppTheme {
        Info(
            icon = Icons.TwoTone.Air,
            iconContentDescription = "Wind icon",
            title = "Wind speed",
            value = "69 m/s",
            small = true,
            modifier = Modifier.padding(16.dp),
        )
    }
}