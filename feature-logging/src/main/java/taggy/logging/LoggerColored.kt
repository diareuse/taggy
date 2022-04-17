package taggy.logging

import java.util.logging.LogRecord
import java.util.logging.Logger

class LoggerColored : Logger("taggy", null) {

    override fun log(record: LogRecord) {
        record.message = withColor(record.level.color.orDefault()) { record.message }
        super.log(record)
    }

}