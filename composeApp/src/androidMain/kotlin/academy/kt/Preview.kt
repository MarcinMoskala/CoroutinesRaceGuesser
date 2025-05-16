package academy.kt

import academy.kt.domain.CoroutinesRacesDifficulty
import academy.kt.domain.GameMode
import academy.kt.domain.GameScreenState
import academy.kt.ui.AnswerScreen
import academy.kt.ui.GameDesign
import academy.kt.ui.GuesserApp
import academy.kt.ui.SelectAnswerScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Preview(device = "id:desktop_medium")
@Composable
private fun AnswerScreenPreview() {
    GameDesign {
        AnswerScreen(
            GameScreenState.Answer(
                difficulty = CoroutinesRacesDifficulty.WithSynchronization,
                livesLeft = 3,
                mode = GameMode.WithSynchronization,
                level = 1,
                numberOfStatements = 5,
                code = """
                coroutineScope {
                    val value1 = async {
                        "D"
                    }
                    println(value1.await())
                }
            """.trimIndent(),
                selectedBlocks = listOf(
                    "A",
                    "(1 sec)",
                    "B",
                    "(2 sec)",
                ),
                correctBlocks = listOf(
                    "A",
                    "(1 sec)",
                    "B",
                    "(2 sec)",
                ),
                isAnswerCorrect = true,
                onNext = { /* No-op for preview */ }
            )
        )
    }
}

//@Preview
//@Composable
//fun SelectAnswerScreenPreview() {
//    SelectAnswerScreen(
//        GameScreenState.SelectAnswer(
//            livesLeft = 3,
//            numberOfStatements = 5,
//            difficulty = coroutines.CoroutinesRacesDifficulty.Simple,
//            code = """
//                coroutineScope {
//                    val value1 = aetsync {
//                        launch {
//                            delay(1000)
//                            println("C")
//                            delay(1000)
//                        }
//                        println("H")
//                        delay(1000)
//                        println("B")
//                        "D"
//                    }
//                    coroutineScope {
//                        launch {
//                            delay(2000)
//                            println("C")
//                            delay(1000)
//                        }
//                        println("H")
//                        delay(1000)
//                        println("A")
//                    }
//                    delay(2000)
//                    println("E")
//                    delay(2000)
//                    println(value1.await())
//                }
//            """.trimIndent(),
//            selectedBlocks = listOf(
//                "A",
//                "(1 sec)",
//                "B",
//                "(2 sec)",
//            ),
//            blocksToSelectFrom = listOf(
//                "A",
//                "B",
//                "C",
//                "D",
//                "(1 sec)",
//                "(2 sec)",
//                "(3 sec)",
//                "(4 sec)",
//                "(5 sec)",
//            ),
//            removeBlockAt = { index ->
//                // No-op for preview
//            },
//            addBlock = { block ->
//                // No-op for preview
//            },
//            giveAnswer = {
//                // No-op for preview
//            },
//        ),
//    )
//}
