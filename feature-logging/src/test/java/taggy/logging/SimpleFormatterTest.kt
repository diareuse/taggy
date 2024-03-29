package taggy.logging

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.logging.Level
import java.util.logging.LogRecord
import kotlin.test.expect

internal class SimpleFormatterTest {

    private lateinit var formatter: SimpleFormatter

    @BeforeEach
    fun setUp() {
        formatter = SimpleFormatter()
    }

    @Test
    fun format() {
        val message = "hello hello!"
        expect(message + System.lineSeparator()) {
            formatter.format(LogRecord(Level.ALL, message))
        }
    }

}