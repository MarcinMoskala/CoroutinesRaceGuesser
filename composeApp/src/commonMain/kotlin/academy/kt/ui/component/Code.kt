package academy.kt.ui.samples.guesser.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import academy.kt.ui.fontSizeMedium
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Code(
    code: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(code, fontSize = fontSizeMedium,
            fontFamily = FontFamily.Monospace)
    }
}

@Preview
@Composable
fun QuestionPreview() {
    Code("""
        [🥕, 🍑, 🍑, 🍒, 🥝, 🌶️, 🍐, 🫑, 🥕, 🥭]
           .sortedByDescending { it.price }
           .dropLast(2)
           .distinct()
           .map { it.name }
           .sortedByDescending { it.length }
           .dropLast(2)
           .distinct()
           .map { it.length }
           .filter { it % 2 == 0 }
           .count()
    """.trimIndent())
}