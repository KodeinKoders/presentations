package ws.utils

import kotlinx.css.TagSelector

fun String.escapeCss() = replace(":", "\\:").replace("@", "\\@").replace(".", "\\.")