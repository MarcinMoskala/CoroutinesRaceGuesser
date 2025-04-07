package academy.kt.ui

import academy.kt.domain.ChallengeRepository
import academy.kt.domain.GameScreenViewModel
import academy.kt.domain.GameScreenState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import coroutines.CoroutinesRacesDifficulty
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun GuesserApp() {
    val challengeRepository = remember { ChallengeRepository() }
    val vm = remember { GameScreenViewModel(challengeRepository) }
    val state = vm.uiState
    when (state) {
        is GameScreenState.Start ->StartScreen(state, vm::startGame)
        GameScreenState.Loading -> Loading()
        is GameScreenState.SelectAnswer -> SelectAnswerScreen(state)
        is GameScreenState.Answer -> AnswerScreen(state)
        is GameScreenState.GameOver -> GameOverScreen(state)
    }
}
