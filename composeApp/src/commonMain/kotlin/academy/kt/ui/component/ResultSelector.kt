package academy.kt.ui.samples.guesser.component

import academy.kt.ui.fontSizeMedium
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.FontFamily
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
            fontSize = fontSizeMedium,
            modifier = Modifier.padding(10.dp)
        )
        FlowRow(
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            blocksToSelectFrom.forEach { option ->
                Button(
                    onClick = { onChosen(option) },
                    modifier = Modifier.padding(5.dp)
                        .focusable()
                        .onKeyEvent {
                            val isEnter = it.key == Key.Enter
                            if (isEnter) {
                                onChosen(option)
                            }
                            isEnter
                        },
                ) {
                    Text(
                        option,
                        fontFamily = FontFamily.Monospace,
                        fontSize = fontSizeMedium,
                    )
                }
            }
            terminalBlocksToSelectFrom.forEach { option ->
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Green,
                        contentColor = Color.White
                    ),
                    onClick = {
                        onChosen(option)
                        onDone()
                    },
                    modifier = Modifier.padding(5.dp)
                        .focusable()
                        .onKeyEvent {
                            val isEnter = it.key == Key.Enter
                            if (isEnter) {
                                onChosen(option)
                                onDone()
                            }
                            isEnter
                        },
                ) {
                    Text(
                        option,
                        fontFamily = FontFamily.Monospace,
                        fontSize = fontSizeMedium,
                    )
                }
            }
        }
    }
}