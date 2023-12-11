package utils

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun AnimatedSlideUpContent(
    step: Int,
    modifier: Modifier = Modifier,
    durationMillis: Int = 600,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable() AnimatedContentScope.(Int) -> Unit
) {

    AnimatedContent(
        targetState = step,
        transitionSpec = {
            if (targetState > initialState) {
                // If the target number is larger, it slides up and fades in
                // while the initial (smaller) number slides up and fades out.
                slideInVertically(tween(durationMillis)) { height -> height } + fadeIn(tween(durationMillis)) togetherWith
                        slideOutVertically(tween(durationMillis)) { height -> -height } + fadeOut(tween(durationMillis))
            } else {
                // If the target number is smaller, it slides down and fades in
                // while the initial number slides down and fades out.
                slideInVertically(tween(durationMillis)) { height -> -height } + fadeIn(tween(durationMillis)) togetherWith
                        slideOutVertically(tween(durationMillis)) { height -> height } + fadeOut(tween(durationMillis))
            }.using(
                // Disable clipping since the faded slide-in/out should
                // be displayed out of bounds.
                SizeTransform(clip = false)
            )
        },
        modifier = modifier,
        contentAlignment = contentAlignment,
        content = content
    )

}