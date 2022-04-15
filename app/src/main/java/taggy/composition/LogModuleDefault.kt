package taggy.composition

import taggy.logging.LoggerColored
import java.util.logging.ConsoleHandler
import java.util.logging.Logger

class LogModuleDefault : LogModule {

    override fun getLogger(): Logger {
        return LoggerColored(ConsoleHandler())
    }

}