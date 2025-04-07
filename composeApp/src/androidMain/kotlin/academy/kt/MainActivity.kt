package academy.kt

import academy.kt.ui.GuesserApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuesserApp()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    GuesserApp()
}