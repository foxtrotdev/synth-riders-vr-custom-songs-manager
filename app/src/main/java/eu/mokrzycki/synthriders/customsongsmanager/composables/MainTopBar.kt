package eu.mokrzycki.synthriders.customsongsmanager.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF5B53C1),
                        Color(0xFF623194)
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            ),
        content = { content() }
    )
}