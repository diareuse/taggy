package taggy.composition

import taggy.logging.LoggerColored
import taggy.logging.SimpleFormatter
import java.util.logging.ConsoleHandler
import java.util.logging.Formatter
import java.util.logging.Handler
import java.util.logging.Logger

class LogModuleDefault : LogModule {

    override fun getLogger(): Logger {
        return LoggerColored(getHandler())
    }

    override fun getHandler(): Handler {
        return ConsoleHandler().also {
            it.formatter = getFormatter()
        }
    }

    override fun getFormatter(): Formatter {
        return SimpleFormatter()
    }

}