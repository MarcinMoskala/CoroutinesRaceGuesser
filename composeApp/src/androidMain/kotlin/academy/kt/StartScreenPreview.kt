package academy.kt

import academy.kt.domain.GameScreenState
import academy.kt.ui.StartScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen(
        GameScreenState.Start(
            startGame = {},
        )
    )
}