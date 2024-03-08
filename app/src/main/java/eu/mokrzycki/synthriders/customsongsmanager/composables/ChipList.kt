package eu.mokrzycki.synthriders.customsongsmanager.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChipList(
    modifier: Modifier = Modifier,
    chips: Map<String, Boolean>,
    onChipSelected: (String, Boolean) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            chips.entries.chunked(2).forEach { chunk ->
                Row {
                    chunk.forEach { entry ->
                        MyChip(
                            modifier = Modifier.weight(1f),
                            text = entry.key,
                            value = entry.value,
                            onChipSelected = onChipSelected
                        )
                        Spacer(modifier.width(16.dp))
                    }
                }
            }
        }
    }
}