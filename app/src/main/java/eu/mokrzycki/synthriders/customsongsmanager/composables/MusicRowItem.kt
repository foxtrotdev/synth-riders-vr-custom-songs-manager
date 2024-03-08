package eu.mokrzycki.synthriders.customsongsmanager.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import eu.mokrzycki.synthriders.customsongsmanager.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MusicRowItem(
    modifier: Modifier = Modifier,
    musicCoverUrl: String?,
    musicDifficulties: List<String>,
    musicTitle: String,
    musicArtist: String,
    musicTime: String,
) {
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .align(Alignment.Top)
            ) {
                GlideImage(
                    model = musicCoverUrl,
                    contentDescription = "coverUrl",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Magenta, CircleShape),
                    loading = placeholder(ColorPainter(Color.Magenta))
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            Box(modifier.padding(top = 16.dp, end = 16.dp)) {
                Column(
                ) {
                    Text(
                        modifier = modifier.padding(bottom = 8.dp),
                        text = musicDifficulties.joinToString(" • "),
                        color = Color.Magenta,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(4.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp,
                        )
                    ) {

                        Column(modifier.padding(16.dp)) {
                            Text(
                                text = musicTitle,
                                style = MaterialTheme.typography.labelLarge,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = musicArtist,
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row {
                                Icon(
                                    modifier = modifier.size(16.dp),
                                    painter = painterResource(id = R.drawable.ic_timer_24dp),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = musicTime,
                                    style = MaterialTheme.typography.labelSmall,
                                    maxLines = 1,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MusicRowItemPreview() {
    MusicRowItem(
        musicCoverUrl = "https://i.vgy.me/OgSWqS.png",
        musicDifficulties = listOf("Easy"),
        musicTitle = "Ciri",
        musicArtist = "Sensei",
        musicTime = "10:10"
    )
}