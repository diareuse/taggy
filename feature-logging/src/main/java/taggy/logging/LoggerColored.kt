package taggy.logging

import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger

class LoggerColored(
    handler: Handler
) : Logger("taggy", null) {

    init {
        addHandler(handler)
    }

    override fun log(record: LogRecord) {
        record.message = withColor(record.level) { record.message }
        super.log(record)
    }

    private inline fun withColor(level: Level?, body: () -> String): String {
        val color = resolveColor(level) ?: return body()
        return buildString {
            append(color)
            append(body())
            append(resetColor())
        }
    }

    private fun resolveColor(level: Level?): String? {
        if (level == null) return null
        return when (level.intValue()) {
            in Level.SEVERE.intValue()..Int.MAX_VALUE -> Red
            in Level.WARNING.intValue() until Level.SEVERE.intValue() -> Yellow
            else -> null
        }
    }

    private fun resetColor() = Reset

    companion object {

        const val Reset = "\u001B[0m"
        const val Red = "\u001B[31m"
        const val Yellow = "\u001B[33m"

    }

}