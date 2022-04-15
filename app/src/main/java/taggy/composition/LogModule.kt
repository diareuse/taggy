package taggy.composition

import java.util.logging.Formatter
import java.util.logging.Handler
import java.util.logging.Logger

interface LogModule {

    fun getLogger(): Logger
    fun getHandler(): Handler
    fun getFormatter(): Formatter

}