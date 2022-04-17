package taggy.logging

import java.util.logging.Level

val Level.color: AnsiColor
    get() = when (intValue()) {
        in Level.SEVERE.intValue()..Int.MAX_VALUE -> AnsiColor.Bold.Red
        in Level.WARNING.intValue() until Level.SEVERE.intValue() -> AnsiColor.Regular.Yellow
        else -> AnsiColor.Regular.White
    }

fun AnsiColor?.orDefault() =
    this ?: AnsiColor.Regular.White

inline fun withColor(color: AnsiColor, body: () -> String) = buildString {
    append(color.tag)
    append(body())
    append(AnsiColor.Reset)
}