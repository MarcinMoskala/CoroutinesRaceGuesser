package academy.kt.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun StartScreen(onStart: () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                "So you think you understand collection processing? Let's check it out!",
                textAlign = TextAlign.Center,
                fontSize = fontSizeMedium,
                modifier = Modifier.padding(20.dp),
            )
            Button(
                onClick = { onStart() },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    "Start",
                    fontSize = fontSizeMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.defaultMinSize(100.dp)
                )
            }
        }
    }
}