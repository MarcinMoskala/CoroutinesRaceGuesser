package academy.kt.ui

import academy.kt.domain.GameScreenState
import academy.kt.ui.component.ResultDisplay
import academy.kt.ui.samples.guesser.component.Code
import academy.kt.ui.samples.guesser.component.Hearts
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnswerScreen(state: GameScreenState.Answer) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Hearts(used = 3 - state.livesLeft, left = state.livesLeft)
            Text(
                text = "Level: ${state.numberOfStatements}",
                fontSize = fontSizeMedium,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Code(state.code)
            Text("Correct answer:")
            ResultDisplay(
                answerGiven = state.correctBlocks,
                onRemove = { i, _ ->  },
            )
            if (!state.isAnswerCorrect) {
                Text("Your answer:")
                ResultDisplay(
                    answerGiven = state.selectedBlocks,
                    onRemove = { i, _ -> },
                )
            }
        }
        Button({ state.onNext() }) {
            Text("Next question")
        }
    }
}