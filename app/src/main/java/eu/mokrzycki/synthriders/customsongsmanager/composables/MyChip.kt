package eu.mokrzycki.synthriders.customsongsmanager.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyChip(
    modifier: Modifier = Modifier,
    text: String,
    value : Boolean = true,
    onChipSelected: (String, Boolean) -> Unit
) {
    var selected by remember { mutableStateOf(value) }

    FilterChip(
        modifier = modifier,
        shape = CircleShape,
        elevation = FilterChipDefaults.filterChipElevation(
            elevation = 8.dp
        ),
        colors = FilterChipDefaults.filterChipColors(
            iconColor = Color(0xFF717171),
            labelColor = Color(0xFFFC53BF),//Color(0xFF717171),
            containerColor = Color(0xFFFFFFFF),//Color(0xFFEBEBEB),

            selectedLeadingIconColor = Color(0xFF717171),//Color(0xFFFC53BF),
            selectedLabelColor =  Color(0xFF717171),//Color(0xFFFC53BF),
            selectedContainerColor = Color(0xFFEBEBEB),//Color(0xFFFFFFFF),
        ),
        onClick = {
            selected = !selected
            onChipSelected(text, selected)
        },
        label = {
            Text(text)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = "Clear difficulty $text",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}