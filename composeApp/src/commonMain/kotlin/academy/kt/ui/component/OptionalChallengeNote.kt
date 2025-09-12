package academy.kt.ui.component

import academy.kt.domain.GameMode
import academy.kt.domain.GameScreenState
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OptionalChallengeNote(mode: GameMode, level: Int) {
    if (mode is GameMode.ChallengeMode) {
        val levelReached = level >= mode.levelToReach
        val text = when {
            !levelReached -> "Reach level ${mode.levelToReach} to complete this challenge!"
            else -> "Congratulations! You have completed this challenge!"
        }
        Text(
            text = text,
            color = if (levelReached) Color.Green else MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}