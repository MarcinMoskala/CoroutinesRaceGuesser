package academy.kt.ui

import academy.kt.domain.ChallengeRepository
import academy.kt.domain.GameMode
import academy.kt.domain.GameScreenState
import academy.kt.domain.GameScreenViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.russhwolf.settings.Settings
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun GuesserApp(settings: Settings, challengeCode: String? = null) {
    val challengeRepository = remember { ChallengeRepository() }
    val vm = remember { GameScreenViewModel(challengeRepository, settings) }
    val state = vm.uiState
    LaunchedEffect(challengeCode) {
        if (challengeCode != null) {
            vm.startChallenge(challengeCode)
        }
    }
    GameDesign {
        when (state) {
            is GameScreenState.Start -> StartScreen(state)
            GameScreenState.Loading -> Loading()
            is GameScreenState.GameStoryDialog -> GameStoryDialogScreen(state)
            is GameScreenState.SelectAnswer -> SelectAnswerScreen(state)
            is GameScreenState.Answer -> AnswerScreen(state)
            is GameScreenState.GameOver if state.mode is GameMode.ChallengeMode -> ChallengeOverScreen(state, state.mode)
            is GameScreenState.GameOver -> GameOverScreen(state)
        }
    }
}
