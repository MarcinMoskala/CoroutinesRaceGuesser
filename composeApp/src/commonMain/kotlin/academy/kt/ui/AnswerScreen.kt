package academy.kt.ui

import academy.kt.domain.GameScreenState
import academy.kt.ui.component.GameButton
import academy.kt.ui.component.ResultDisplay
import academy.kt.ui.samples.guesser.component.Code
import academy.kt.ui.samples.guesser.component.Hearts
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coroutinesraceguesser.composeapp.generated.resources.Res
import coroutinesraceguesser.composeapp.generated.resources.customer_service
import coroutinesraceguesser.composeapp.generated.resources.happy
import coroutinesraceguesser.composeapp.generated.resources.happy_face
import coroutinesraceguesser.composeapp.generated.resources.heart_empty
import coroutinesraceguesser.composeapp.generated.resources.heart_eyes
import coroutinesraceguesser.composeapp.generated.resources.heart_full
import coroutinesraceguesser.composeapp.generated.resources.star
import coroutinesraceguesser.composeapp.generated.resources.sunglasses
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import kotlin.random.Random

@OptIn(ExperimentalLayoutApi::class)
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
                text = "Level: ${state.level}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            FlowRow(
                verticalArrangement = Arrangement.Center,
            ) {
                Code(state.code, selectable = true)
                if (state.isAnswerCorrect) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        ResultDisplay(
                            answerGiven = state.correctBlocks,
                            onRemove = { i, _ -> },
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            "Incorrect",
                            color = Color.Red,
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.padding(8.dp)
                        )
                        ResultDisplay(
                            answerGiven = state.selectedBlocks,
                            onRemove = { i, _ -> },
                        )
                    }
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            "Correct answer:",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.padding(8.dp)
                        )
                        ResultDisplay(
                            answerGiven = state.correctBlocks,
                            onRemove = { i, _ -> },
                        )
                    }
                }
            }
            if (state.isAnswerCorrect) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(remember { imageResources.random() }),
                        contentDescription = "Success image",
                        modifier = Modifier.size(200.dp)
                    )
                    Text(
                        "Correct!",
                        color = orangeColor,
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        GameButton(
            text = "Next question",
            onClick = { state.onNext() },
            modifier = Modifier.padding(20.dp),
        )
    }
}

private val imageResources = listOf(
    Res.drawable.customer_service,
    Res.drawable.happy,
    Res.drawable.happy_face,
    Res.drawable.heart_eyes,
    Res.drawable.star,
    Res.drawable.sunglasses,
)

