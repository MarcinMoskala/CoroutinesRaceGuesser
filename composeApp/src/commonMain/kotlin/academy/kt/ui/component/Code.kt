package academy.kt.ui.samples.guesser.component

import academy.kt.ui.orangeColor
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.text.Regex.Companion.fromLiteral

@Composable
fun Code(
    code: String,
    modifier: Modifier = Modifier,
    selectable: Boolean = false,
) {
    val onBackground = MaterialTheme.colorScheme.onBackground
    val codeAnnotated = remember(code) {
        val simple = SpanStyle(onBackground)
        val value = SpanStyle(Color(0xFF4A86E8))
        val keyword = SpanStyle(orangeColor)
        val string = SpanStyle(Color(0xFF6aab73))
//        val annotation = SpanStyle(Color(0xFFBBB529))
//        val comment = SpanStyle(Color(0xFF808080))
        buildAnnotatedString {
            withStyle(simple) {
                append(code)
                addStyle(keyword, code, "suspend ")
                addStyle(keyword, code, "fun ")
                addStyle(keyword, code, "val ")
                addStyle(keyword, code, "true")
                addStyle(keyword, code, "false")
//            addStyle(keyword, code, "var ")
//            addStyle(keyword, code, "private ")
//            addStyle(keyword, code, "internal ")
//            addStyle(keyword, code, "for ")
//            addStyle(keyword, code, "expect ")
//            addStyle(keyword, code, "actual ")
//            addStyle(keyword, code, "import ")
//            addStyle(keyword, code, "package ")
                addStyle(value, code, "true")
                addStyle(value, code, "false")
//                addStyle(value, code, Regex("[0-9]+"))
                addStyle(string, code, Regex("\"[a-zA-Z_]*\""))
//                addStyle(comment, code, Regex("^\\s*//.*"))

                // Keeps copied lines separated and fixes crash during selection:
                // https://partnerissuetracker.corp.google.com/issues/199919707
                append("\n")
            }
        }
    }
    Column(modifier = modifier.padding(16.dp)) {
        SelectionContainer(enabled = selectable) {
            Text(
                codeAnnotated,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}

@Composable
private fun SelectionContainer(
    enabled: Boolean,
    content: @Composable () -> Unit,
) {
    if (enabled) {
        SelectionContainer(content = content)
    } else {
        content()
    }
}

private fun AnnotatedString.Builder.addStyle(style: SpanStyle, text: String, regexp: String) {
    addStyle(style, text, fromLiteral(regexp))
}

private fun AnnotatedString.Builder.addStyle(style: SpanStyle, text: String, regexp: Regex) {
    for (result in regexp.findAll(text)) {
        addStyle(style, result.range.first, result.range.last + 1)
    }
}

@Preview
@Composable
private fun QuestionPreview() {
    Code(
        """
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
    """.trimIndent()
    )
}