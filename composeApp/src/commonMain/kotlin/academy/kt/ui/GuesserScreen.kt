package academy.kt.ui

import academy.kt.domain.GameOver
import academy.kt.domain.GameState
import academy.kt.domain.Playing
import academy.kt.domain.Start
import academy.kt.domain.onAnswerGiven
import academy.kt.domain.start
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import coroutines.CoroutinesRacesDifficulty
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun GuesserScreen() {
    var gameState by remember { mutableStateOf<GameState>(Start) }
    when (val state = gameState) {
        is Start -> StartScreen(
            onStart = { gameState = start(CoroutinesRacesDifficulty.Simple) }
        )

        is Playing -> GameScreen(
            questionNumber = state.questionNumber,
            level = state.level,
            difficulty = state.difficulty,
            livesUsed = state.livesUsed,
            livesLeft = state.livesLeft,
            onAnswer = { gameState = onAnswerGiven(state, it) }
        )

        is GameOver -> GameOverScreen(
            level = state.score,
            onPlayAgain = { gameState = start(CoroutinesRacesDifficulty.Simple) }
        )
    }
}
