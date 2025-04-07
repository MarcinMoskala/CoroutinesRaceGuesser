package academy.kt.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import coroutines.CoroutinesRaceChallenge
import coroutines.CoroutinesRacesDifficulty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class GameScreenViewModel(
    private val challengeRepository: ChallengeRepository,
) {
    var uiState by mutableStateOf<GameScreenState>(GameScreenState.Start)
        private set

    val viewModelScope = CoroutineScope(SupervisorJob())

    fun startGame(difficulty: CoroutinesRacesDifficulty) {
        toNextChallenge(
            difficulty = difficulty,
            numberOfStatements = 5,
            livesLeft = 3,
        )
    }

    private fun toNextChallenge(
        difficulty: CoroutinesRacesDifficulty,
        numberOfStatements: Int,
        livesLeft: Int
    ) {
        viewModelScope.launch {
            uiState = GameScreenState.Loading
            val challenge = challengeRepository.getChallenge(numberOfStatements, difficulty)
            uiState = GameScreenState.SelectAnswer(
                difficulty = difficulty,
                livesLeft = livesLeft,
                numberOfStatements = numberOfStatements,
                code = challenge.code,
                selectedBlocks = emptyList(),
                blocksToSelectFrom = challenge.possibleAnswers,
                removeBlockAt = { index ->
                    val state = uiState as GameScreenState.SelectAnswer
                    uiState = state.copy(
                        selectedBlocks = state.selectedBlocks.toMutableList()
                            .apply { removeAt(index) }
                    )
                },
                addBlock = { block ->
                    val state = uiState as GameScreenState.SelectAnswer
                    uiState = state.copy(
                        selectedBlocks = state.selectedBlocks + block
                    )
                },
                giveAnswer = {
                    val state = uiState as GameScreenState.SelectAnswer
                    showAnswer(state, challenge)
                }
            )
        }
    }

    private fun showAnswer(
        state: GameScreenState.SelectAnswer,
        challenge: CoroutinesRaceChallenge
    ) {
        uiState = GameScreenState.Answer(
            difficulty = state.difficulty,
            livesLeft = state.livesLeft,
            numberOfStatements = state.numberOfStatements,
            code = state.code,
            selectedBlocks = state.selectedBlocks,
            correctBlocks = challenge.sequentialResult,
            isAnswerCorrect = state.selectedBlocks == challenge.sequentialResult,
            onNext = { onNext(uiState as GameScreenState.Answer) }
        )
    }

    private fun onNext(state: GameScreenState.Answer) {
        when {
            state.isAnswerCorrect -> {
                toNextChallenge(
                    difficulty = state.difficulty,
                    numberOfStatements = state.numberOfStatements + 1,
                    livesLeft = state.livesLeft
                )
            }
            state.livesLeft == 0 -> {
                uiState = GameScreenState.GameOver(
                    numberOfStatements = state.numberOfStatements
                )
            }
            else -> {
                toNextChallenge(
                    difficulty = state.difficulty,
                    numberOfStatements = state.numberOfStatements,
                    livesLeft = state.livesLeft - 1
                )
            }
        }

    }
}

sealed class GameScreenState {
    object Loading : GameScreenState()

    object Start : GameScreenState()

    data class SelectAnswer(
        val difficulty: CoroutinesRacesDifficulty,
        val livesLeft: Int,
        val numberOfStatements: Int,
        val code: String,
        val selectedBlocks: List<String>,
        val blocksToSelectFrom: List<String>,
        val removeBlockAt: (Int) -> Unit,
        val addBlock: (String) -> Unit,
        val giveAnswer: () -> Unit,
    ) : GameScreenState()

    data class Answer(
        val difficulty: CoroutinesRacesDifficulty,
        val livesLeft: Int,
        val numberOfStatements: Int,
        val code: String,
        val selectedBlocks: List<String>,
        val correctBlocks: List<String>,
        val isAnswerCorrect: Boolean,
        val onNext: () -> Unit,
    ) : GameScreenState()

    data class GameOver(
        val numberOfStatements: Int,
    ) : GameScreenState()
}