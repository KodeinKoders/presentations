package theme

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.kodein.pres.ui.SourceCodeBuilder
import java.awt.Desktop
import java.net.URI


@Composable
fun Title(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current.copy(
            fontSize = 1.6.em,
            fontFamily = FontFamily.Picon.Extended,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
        )
    ) {
        content()
    }
}

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier
) {
    Title { Text(text, modifier) }
}

@Composable
fun Title(
    text: AnnotatedString,
    modifier: Modifier = Modifier
) {
    Title { Text(text, modifier) }
}


private const val LINK_TAG = "link"

@OptIn(ExperimentalTextApi::class)
fun AnnotatedString.Builder.appendLink(url: String, text: String? = null) {
    withStyle(SpanStyle(
        color = Color.Kodein.Salmon,
        textDecoration = TextDecoration.Underline
    )) {
        withAnnotation(LINK_TAG, url) {
            append(text ?: url.removePrefix("https://"))
        }
    }
}

@Composable
fun TextWithLinks(
    text: AnnotatedString,
    style: TextStyle = TextStyle.Default,
    modifier: Modifier = Modifier
) {
    ClickableText(
        text = text,
        style = style,
        modifier = modifier.pointerHoverIcon(PointerIcon.Hand),
        onClick = { offset ->
            text.getStringAnnotations(LINK_TAG, offset, offset).firstOrNull()?.let { range ->
                Desktop.getDesktop().browse(URI(range.item))
            }
        }
    )
}

@Composable
fun URLText(
    url: String,
    text: String? = null,
    style: TextStyle = TextStyle.Default,
    modifier: Modifier = Modifier
) {
    val aText = buildAnnotatedString {
        appendLink(url, text)
    }
    TextWithLinks(
        text = aText,
        style = style,
        modifier = modifier
    )
}
