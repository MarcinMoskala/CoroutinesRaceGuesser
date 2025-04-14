package academy.kt.ui.component

import academy.kt.ui.blueColor
import academy.kt.ui.fontSizeMedium
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun ResultDisplay(
    answerGiven: List<String>,
    onRemove: (Int, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(12.dp)
    ) {
        for ((i, answer) in answerGiven.withIndex()) {
            Button(
                onClick = { onRemove(i, answer) },
                modifier = Modifier.padding(5.dp),
                colors = buttonColors(
                    backgroundColor = blueColor,
                    contentColor = MaterialTheme.colors.onPrimary
                ),
            ) {
                Text(
                    answer,
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}