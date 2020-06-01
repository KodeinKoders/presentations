package ws.utils

import kotlinx.css.TagSelector

val li get() = TagSelector("li")

fun String.escapeCss() = replace(":", "\\:").replace("@", "\\@").replace(".", "\\.")