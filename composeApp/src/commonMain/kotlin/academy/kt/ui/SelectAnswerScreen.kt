package academy.kt.ui

import academy.kt.domain.GameScreenState
import academy.kt.ui.component.ResultDisplay
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import academy.kt.ui.samples.guesser.component.Code
import academy.kt.ui.samples.guesser.component.Hearts
import academy.kt.ui.samples.guesser.component.ResultSelector
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectAnswerScreen(
    state: GameScreenState.SelectAnswer,
) {
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
            Text("What is the result of this code?")
            ResultDisplay(
                answerGiven = state.selectedBlocks,
                onRemove = { i, _ -> state.removeBlockAt(i) },
            )
        }
        ResultSelector(
            possibleAnswers = state.blocksToSelectFrom,
            onChosen = { state.addBlock(it) },
            onDone = { state.giveAnswer() },
            modifier = Modifier.padding(bottom = 30.dp)
        )
    }
}