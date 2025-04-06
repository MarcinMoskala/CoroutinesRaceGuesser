package academy.kt.ui

import academy.kt.domain.Level
import academy.kt.ui.component.ResultDisplay
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import academy.kt.ui.samples.guesser.component.Code
import academy.kt.ui.samples.guesser.component.Hearts
import academy.kt.ui.samples.guesser.component.ResultSelector
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import coroutines.CoroutinesRaceChallenge
import coroutines.CoroutinesRacesDifficulty
import coroutines.generateChallenge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GameScreen(
    questionNumber: Int,
    level: Level,
    difficulty: CoroutinesRacesDifficulty,
    livesUsed: Int,
    livesLeft: Int,
    onAnswer: (Boolean) -> Unit = {},
) {
    val (game, setGame) = remember(questionNumber) {
        mutableStateOf<CoroutinesRaceChallenge?>(
            null
        )
    }
    LaunchedEffect(questionNumber) {
        launch(Dispatchers.Default) {
            setGame(generateChallenge(level.value, difficulty))
        }
    }
    if (game == null) {
        Loading()
    } else {
        GameScreen(
            challenge = game,
            questionNumber = questionNumber,
            level = level,
            livesUsed = livesUsed,
            livesLeft = livesLeft,
            onAnswer = onAnswer,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameScreen(
    challenge: CoroutinesRaceChallenge,
    questionNumber: Int,
    level: Level,
    livesUsed: Int,
    livesLeft: Int,
    onAnswer: (Boolean) -> Unit = {},
) {
    var answerGiven by remember { mutableStateOf(listOf<String>()) }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Hearts(livesUsed, livesLeft)
            Text(
                text = "Level: ${level.value}",
                fontSize = fontSizeMedium,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Code(challenge.code)
            Text("What is the result of this code?")
            ResultDisplay(
                answerGiven = answerGiven,
                onRemove = { i, _ -> answerGiven = answerGiven.toMutableList().apply { removeAt(i) } },
            )
        }
        ResultSelector(
            possibleAnswers = challenge.possibleAnswers,
            onChosen = { answerGiven += it },
            modifier = Modifier.padding(bottom = 30.dp)
        )
    }
}

@Preview
@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp)
        )
    }
}