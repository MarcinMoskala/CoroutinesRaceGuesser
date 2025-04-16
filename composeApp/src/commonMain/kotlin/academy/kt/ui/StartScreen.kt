package academy.kt.ui

import academy.kt.domain.CoroutinesRacesDifficulty
import academy.kt.domain.GameMode
import academy.kt.domain.GameScreenState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StartScreen(state: GameScreenState.Start) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "So you think you understand Kotlin Coroutines? Let's check it out!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(20.dp),
            )
            Text(
                "What kind kind of challenge do you want to face?",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(20.dp),
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(12.dp),
            ) {
                for (difficulty in GameMode.entries) {
                    Button(
                        onClick = { state.startGame(difficulty) },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            difficulty.displayName,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.defaultMinSize(100.dp)
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}