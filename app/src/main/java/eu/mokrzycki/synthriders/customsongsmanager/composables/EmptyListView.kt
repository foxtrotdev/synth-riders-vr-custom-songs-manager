package eu.mokrzycki.synthriders.customsongsmanager.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import eu.mokrzycki.synthriders.customsongsmanager.R
import eu.mokrzycki.synthriders.customsongsmanager.theme.SynthRiderMusicManagerTheme

val NO_FILES = AnnotatedString.Builder()
    .apply {
        append("No files ")
        pushStyle(
            SpanStyle(
                fontWeight = FontWeight.Bold
            )
        )
        append("*.synth")
        pushStyle(
            SpanStyle(
                fontWeight = FontWeight.Light
            )
        )
        append(" found in folder ")
        pushStyle(
            SpanStyle(
                fontWeight = FontWeight.Bold
            )
        )
        append("CustomSongs")
    }.toAnnotatedString()

val NO_MATCHING_SONGS = AnnotatedString.Builder("No matching songs found...").toAnnotatedString()

@Composable
fun EmptyListView(annotatedString: AnnotatedString) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))

    Box(
        Modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically),
    ) {
        LottieAnimation(
            modifier = Modifier
                .padding(bottom = 150.dp)
                .size(200.dp)
                .align(Alignment.Center),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        Text(
            text = annotatedString,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Center),
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            color = Color(0xFF960096)
        )
    }
}


@Preview
@Composable
fun EmptyListAnimationPrev() {
    SynthRiderMusicManagerTheme {
        EmptyListView(NO_FILES)
    }
}