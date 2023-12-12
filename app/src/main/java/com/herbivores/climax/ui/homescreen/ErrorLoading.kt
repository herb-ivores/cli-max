package com.herbivores.climax.ui.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.twotone.SignalWifiBad
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.herbivores.climax.ui.theme.AppTheme
import com.thebrownfoxx.components.FilledButton
import com.thebrownfoxx.components.VerticalSpacer

@Composable
fun ErrorLoading(
    onReload: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.TwoTone.SignalWifiBad,
            contentDescription = "No internet icon",
            modifier = Modifier.size(48.dp),
        )
        VerticalSpacer(height = 16.dp)
        Text(
            text = "No internet connection",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
        )
        VerticalSpacer(height = 16.dp)
        FilledButton(
            icon = Icons.Default.Refresh,
            text = "Reload",
            onClick = onReload,
        )
    }
}

@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Preview
@Composable
fun ErrorLoadingPreview() {
    AppTheme {
        Surface {
            ErrorLoading(
                onReload = {},
                modifier = Modifier.padding(64.dp),
            )
        }
    }
}