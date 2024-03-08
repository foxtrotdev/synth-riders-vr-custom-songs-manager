package eu.mokrzycki.synthriders.customsongsmanager.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.mokrzycki.synthriders.customsongsmanager.theme.SynthRiderMusicManagerTheme

@Composable
fun ToolbarSecondRow(
    modifier: Modifier = Modifier,
    songs: Pair<Int, Int>,
    chips: Map<String, Boolean>,
    onChipSelected: (String, Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        CircleIndicator(
            songs = songs,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier.width(16.dp))
        ChipList(
            chips = chips,
            onChipSelected = onChipSelected
        )
    }
    Spacer(modifier.heightIn(16.dp))
}

@Preview
@Composable
fun ToolbarSecondRowPreview() {
    val chips = remember {
        mutableStateMapOf(
            "Easy" to true,
            "Normal" to true,
            "Hard" to true,
            "Expert" to true,
            "Master" to true,
            "Custom" to true,
        )
    }

    SynthRiderMusicManagerTheme {
        ToolbarSecondRow(modifier = Modifier, songs = Pair(99, 99), chips = chips) { _, _ -> }
    }
}