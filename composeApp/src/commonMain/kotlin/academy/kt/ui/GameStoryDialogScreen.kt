package academy.kt.ui

import academy.kt.domain.GameScreenState.GameStoryDialog
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coroutinesraceguesser.composeapp.generated.resources.Res
import coroutinesraceguesser.composeapp.generated.resources.wizard_marcin
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun GameStoryDialogScreen(state: GameStoryDialog) {
    GameStoryDialogScreen(
        dialogs = state.dialogs,
        onNext = state.onNext,
    )
}

@Composable
fun GameStoryDialogScreen(
    dialogs: List<String>,
    onNext: () -> Unit,
) {
    var text: String? by remember { mutableStateOf(null) }
    LaunchedEffect(dialogs) {
        delay(1000)
        for (dialog in dialogs) {
            text = dialog
            delay(dialog.length * 70L + 1000)
        }
        text = null
    }
    GamePlot(text, onNext)
}

@Composable
fun GamePlot(
    text: String?,
    onPlay: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(Res.drawable.wizard_marcin),
            contentDescription = "Wizard",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.Center)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            AnimatedContent(
                text,
                transitionSpec = {
                    fadeIn(tween(700)) + slideIn(initialOffset = { IntOffset(0, -400) }) togetherWith
                            fadeOut(tween(700)) + slideOut(targetOffset = { IntOffset(0, 400) })
                },
            ) {
                if (it != null) {
                    TextDialog(text = it)
                } else {
                    Box(modifier = Modifier.size(200.dp))
                }
            }
            PlayButton(
                onPlay = onPlay,
//                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
private fun TextDialog(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onSecondary,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(blueColor)
            .padding(16.dp)
    )
}

@Composable
private fun PlayButton(
    onPlay: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onPlay,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.buttonElevation(8.dp),
        modifier = modifier,
    ) {
        Text(
            text = "Continue",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}
