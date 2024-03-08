package eu.mokrzycki.synthriders.customsongsmanager.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import eu.mokrzycki.synthriders.customsongsmanager.R

private const val GITHUB_URL = "https://github.com/foxtrotdev/synth-riders-vr-custom-songs-manager"

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ToolbarFirstRow() {
    val context = LocalContext.current
    val appName = context.getString(R.string.app_name)
    val sourceCodeDescription = stringResource(R.string.action_source_code)

    SmallTopAppBar(
        title = { Text(text = appName) },
        actions = {
            IconButton(onClick = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(GITHUB_URL)
                }
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_github),
                    contentDescription = sourceCodeDescription
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "")
            }
        },
    )
}