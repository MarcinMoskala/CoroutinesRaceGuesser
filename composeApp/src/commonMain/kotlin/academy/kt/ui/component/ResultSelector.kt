package academy.kt.ui.samples.guesser.component

import academy.kt.ui.component.GameButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ResultSelector(
    blocksToSelectFrom: List<String>,
    terminalBlocksToSelectFrom: List<String>,
    onChosen: (String) -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Center,
        horizontalAlignment = CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            "Choose what will happen next from those options:",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(16.dp)
        )
        FlowRow(
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            blocksToSelectFrom.forEach { option ->
                GameButton(
                    text = option,
                    onClick = { onChosen(option) },
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
            terminalBlocksToSelectFrom.forEach { option ->
                GameButton(
                    text = option,
                    onClick = {
                        onChosen(option)
                        onDone()
                    },
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}