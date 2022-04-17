package taggy.logging

import java.util.logging.Formatter
import java.util.logging.Handler
import java.util.logging.LogRecord

class SimpleFormatter : Formatter() {

    override fun format(record: LogRecord): String {
        return record.message.orEmpty()
    }

    override fun getTail(h: Handler?): String {
        return System.lineSeparator()
    }

}