package eu.mokrzycki.synthriders.customsongsmanager.composables

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import eu.mokrzycki.synthriders.customsongsmanager.R
import eu.mokrzycki.synthriders.customsongsmanager.theme.SynthRiderMusicManagerTheme

val FETCHING = AnnotatedString.Builder("Fetching songs...").toAnnotatedString()
val SYNCING = AnnotatedString.Builder("Syncing songs...").toAnnotatedString()

@Composable
fun LoadingListView(progress: Float, text: AnnotatedString) {
    Log.d("synthrider", "progress: $progress")
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.progress))

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LottieAnimation(
            modifier = Modifier.wrapContentHeight(),
            composition = composition,
            progress = {
                val offset = 0.0825f // 10% offset
                val offsetProgress = (progress - offset).coerceIn(0f, 1f)
                offsetProgress
            }
        )

        Text(
            text = text,
            color = Color.Black,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}

@Preview
@Composable
fun LoadingListAnimationPreview() {
    SynthRiderMusicManagerTheme {
        LoadingListView(
            progress = 0f,
            text = FETCHING
        )
    }
}