package academy.kt.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.to

class GameScreenViewModel(
    private val challengeRepository: ChallengeRepository,
) {
    private val checkAnswerUseCase = CheckAnswerUseCase()
    var uiState by mutableStateOf<GameScreenState>(GameScreenState.Start(::startGame))
        private set

    val viewModelScope = CoroutineScope(SupervisorJob())

    private fun startGame(mode: GameMode) {
        toNextChallenge(
            mode = mode,
            level = 1,
            livesLeft = 3,
        )
    }

    private fun toNextChallenge(
        mode: GameMode,
        level: Int,
        livesLeft: Int,
        storyShown: Boolean = false,
    ) {
        viewModelScope.launch {
            if (mode == GameMode.Story && !storyShown) {
                val storyDialog = getStoryDialog(level)
                if (storyDialog != null) {
                    uiState = GameScreenState.GameStoryDialog(
                        dialogs = storyDialog,
                        onNext = {
                            toNextChallenge(
                                mode = mode,
                                level = level,
                                livesLeft = livesLeft,
                                storyShown = true
                            )
                        }
                    )
                    return@launch
                }
            }
            val difficulty = difficultyByMode(mode, level)
            val numberOfStatements = numberOfStatementsByMode(mode, level)
            uiState = GameScreenState.Loading
            val challenge = challengeRepository.getChallenge(numberOfStatements, difficulty)
            uiState = GameScreenState.SelectAnswer(
                difficulty = difficulty,
                livesLeft = livesLeft,
                numberOfStatements = numberOfStatements,
                mode = mode,
                level = level,
                code = challenge.code,
                selectedBlocks = emptyList(),
                blocksToSelectFrom = challenge.possibleAnswers - TERMINAL_BLOCKS,
                terminalBlocksToSelectFrom = TERMINAL_BLOCKS_TO_CONSIDER[difficulty]?.toSet()
                    .orEmpty()
                    .plus(challenge.possibleAnswers.filter { it in TERMINAL_BLOCKS })
                    .toList(),
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

    private fun difficultyByMode(mode: GameMode, level: Int) = when (mode) {
        GameMode.Story -> when (level) {
            in 1..3 -> CoroutinesRacesDifficulty.Simple
            in 4..6 -> CoroutinesRacesDifficulty.WithSynchronization
            in 7..9 -> CoroutinesRacesDifficulty.WithExceptions
            else -> CoroutinesRacesDifficulty.WithSynchronizationAndExceptions
        }

        GameMode.Simple -> CoroutinesRacesDifficulty.Simple
        GameMode.WithSynchronization -> CoroutinesRacesDifficulty.WithSynchronization
        GameMode.WithExceptions -> CoroutinesRacesDifficulty.WithExceptions
        GameMode.SurvivalMode -> CoroutinesRacesDifficulty.WithSynchronizationAndExceptions
    }

    private fun numberOfStatementsByMode(mode: GameMode, level: Int) = when (mode) {
        GameMode.Story -> if(level < 10) 5 + level else level * 2 - 5
        GameMode.Simple -> 5 + level
        GameMode.WithSynchronization -> 8 + level
        GameMode.WithExceptions -> 8 + level
        GameMode.SurvivalMode -> 10 + level * 2
    }

    private fun getStoryDialog(nextLevel: Int): List<String>? = when (nextLevel) {
        1 -> listOf(
            "So you are the new pretender to become a master of Kotlin Coroutines?",
            "Let's see how you do with basic structures..."
        )
        4 -> listOf("Not bed, but it is time to add some synchronization mechanisms...")
        7 -> listOf("Nice, let's see if exceptions are also your thing...")
        10 -> listOf("I am impressed, but let's see if you can handle everything at once, with a bit more statements...")
        else -> null
    }

    private fun showAnswer(
        state: GameScreenState.SelectAnswer,
        challenge: CoroutinesRaceChallenge
    ) {
        uiState = GameScreenState.Answer(
            difficulty = state.difficulty,
            livesLeft = state.livesLeft,
            mode = state.mode,
            level = state.level,
            numberOfStatements = state.numberOfStatements,
            code = state.code,
            selectedBlocks = state.selectedBlocks,
            correctBlocks = challenge.sequentialResult,
            isAnswerCorrect = checkAnswerUseCase.isCorrectAnswer(
                state.selectedBlocks,
                challenge.sequentialResult
            ),
            onNext = { onNext(uiState as GameScreenState.Answer) }
        )
    }

    private fun onNext(state: GameScreenState.Answer) {
        when {
            state.isAnswerCorrect -> {
                toNextChallenge(
                    mode = state.mode,
                    level = state.level + 1,
                    livesLeft = state.livesLeft
                )
            }

            state.livesLeft <= 1 -> {
                uiState = GameScreenState.GameOver(
                    mode = state.mode,
                    level = state.level,
                    startAgain = {
                        uiState = GameScreenState.Start(::startGame)
                    },
                )
            }

            else -> {
                toNextChallenge(
                    mode = state.mode,
                    level = state.level,
                    livesLeft = state.livesLeft - 1
                )
            }
        }
    }

    companion object {
        val TERMINAL_BLOCKS = listOf(
            "(done)",
            "(exception)",
            "(cancellation exception)",
            "(waiting forever)"
        )
        val TERMINAL_BLOCKS_TO_CONSIDER = mapOf(
            CoroutinesRacesDifficulty.Simple to listOf("(done)"),
            CoroutinesRacesDifficulty.WithExceptions to listOf(
                "(done)",
                "(exception)",
                "(cancellation exception)"
            ),
            CoroutinesRacesDifficulty.WithSynchronization to listOf("(done)", "(waiting forever)"),
            CoroutinesRacesDifficulty.WithSynchronizationAndExceptions to TERMINAL_BLOCKS,
        )
    }
}

sealed class GameScreenState {
    object Loading : GameScreenState()

    data class Start(
        val startGame: (GameMode) -> Unit
    ) : GameScreenState()

    data class GameStoryDialog(
        val dialogs: List<String>,
        val onNext: () -> Unit,
    ) : GameScreenState()

    data class SelectAnswer(
        val difficulty: CoroutinesRacesDifficulty,
        val livesLeft: Int,
        val mode: GameMode,
        val level: Int,
        val numberOfStatements: Int,
        val code: String,
        val selectedBlocks: List<String>,
        val blocksToSelectFrom: List<String>,
        val terminalBlocksToSelectFrom: List<String>,
        val removeBlockAt: (Int) -> Unit,
        val addBlock: (String) -> Unit,
        val giveAnswer: () -> Unit,
    ) : GameScreenState()

    data class Answer(
        val difficulty: CoroutinesRacesDifficulty,
        val livesLeft: Int,
        val mode: GameMode,
        val level: Int,
        val numberOfStatements: Int,
        val code: String,
        val selectedBlocks: List<String>,
        val correctBlocks: List<String>,
        val isAnswerCorrect: Boolean,
        val onNext: () -> Unit,
    ) : GameScreenState()

    data class GameOver(
        val mode: GameMode,
        val level: Int,
        val startAgain: () -> Unit,
    ) : GameScreenState()
}

enum class GameMode(val displayName: String) {
    Story("Adventure"),
    Simple("Simple"),
    WithSynchronization("Medium with Synchronization"),
    WithExceptions("Medium with Exceptions"),
    SurvivalMode("Expert!"),
}