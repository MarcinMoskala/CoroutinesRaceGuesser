package academy.kt

import academy.kt.ui.GuesserApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.AndroidUiDispatcher
import androidx.compose.ui.tooling.preview.Preview
import com.russhwolf.settings.SharedPreferencesSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferenceSettings = SharedPreferencesSettings(getSharedPreferences("settings", MODE_PRIVATE))

        setContent {
            GuesserApp(preferenceSettings)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    GuesserApp()
}