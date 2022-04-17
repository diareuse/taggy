package taggy.logging

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mockingDetails
import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.LogRecord
import kotlin.test.expect

internal class LoggerColoredTest {

    private lateinit var logger: LoggerColored

    @Mock
    lateinit var handler: Handler

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        logger = LoggerColored()
        logger.level = Level.ALL
        logger.addHandler(handler)
    }

    @Test
    fun `log severe`() {
        logger.severe { "severe" }
        handler.verifyPublish(Level.SEVERE, "\u001B[1;31msevere\u001B[0m")
    }

    @Test
    fun `log warning`() {
        logger.warning { "warning" }
        handler.verifyPublish(Level.WARNING, "\u001B[0;33mwarning\u001B[0m")
    }

    @Test
    fun `log INFO`() {
        logger.info { "info" }
        handler.verifyPublish(Level.INFO, "\u001B[0;37minfo\u001B[0m")
    }

    @Test
    fun `log CONFIG`() {
        logger.config { "config" }
        handler.verifyPublish(Level.CONFIG, "\u001B[0;37mconfig\u001B[0m")
    }

    @Test
    fun `log FINE`() {
        logger.fine { "fine" }
        handler.verifyPublish(Level.FINE, "\u001B[0;37mfine\u001B[0m")
    }

    @Test
    fun `log FINER`() {
        logger.finer { "finer" }
        handler.verifyPublish(Level.FINER, "\u001B[0;37mfiner\u001B[0m")
    }

    @Test
    fun `log FINEST`() {
        logger.finest { "finest" }
        handler.verifyPublish(Level.FINEST, "\u001B[0;37mfinest\u001B[0m")
    }

    // ---

    private fun Handler.verifyPublish(level: Level, message: String) = expect(message) {
        mockingDetails(this).invocations
            .filter { it.method.name == "publish" }
            .map { it.arguments.first() as LogRecord }
            .firstOrNull { it.level == level }
            ?.message
    }

}