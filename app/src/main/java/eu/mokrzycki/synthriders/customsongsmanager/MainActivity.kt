package eu.mokrzycki.synthriders.customsongsmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import eu.mokrzycki.synthriders.customsongsmanager.theme.SynthRiderMusicManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SynthRiderMusicManagerTheme {
                TodoNavGraph()
            }
        }
    }
}

