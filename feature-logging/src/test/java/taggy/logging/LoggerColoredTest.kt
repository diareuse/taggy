package taggy.logging

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mockingDetails
import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.LogRecord

internal class LoggerColoredTest {

    private lateinit var logger: LoggerColored

    @Mock
    lateinit var handler: Handler

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        logger = LoggerColored(handler)
        logger.level = Level.ALL
    }

    @Test
    fun `log severe`() {
        logger.severe { "" }
        handler.verifyPublish(Level.SEVERE, "\u001B[31m\u001B[0m")
    }

    @Test
    fun `log warning`() {
        logger.warning { "" }
        handler.verifyPublish(Level.WARNING, "\u001B[33m\u001B[0m")
    }

    @Test
    fun `log INFO`() {
        logger.info { "" }
        handler.verifyPublish(Level.INFO, "")
    }

    @Test
    fun `log CONFIG`() {
        logger.config { "" }
        handler.verifyPublish(Level.CONFIG, "")
    }

    @Test
    fun `log FINE`() {
        logger.fine { "" }
        handler.verifyPublish(Level.FINE, "")
    }

    @Test
    fun `log FINER`() {
        logger.finer { "" }
        handler.verifyPublish(Level.FINER, "")
    }

    @Test
    fun `log FINEST`() {
        logger.finest { "" }
        handler.verifyPublish(Level.FINEST, "")
    }

    // ---

    private fun Handler.verifyPublish(level: Level, message: String) = mockingDetails(this).invocations
        .filter { it.method.name == "publish" }
        .map { it.arguments.first() as LogRecord }
        .first { it.level == level && it.message == message }

}