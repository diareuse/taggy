package taggy.provider

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import taggy.test.TestSuccessful
import java.io.ByteArrayInputStream
import java.io.InputStream

internal class ConsoleDefaultTest {

    private lateinit var console: ConsoleDefault

    @Mock
    lateinit var runtime: Runtime

    @Mock
    lateinit var process: Process

    @Mock
    lateinit var inputStream: InputStream

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        whenever(runtime.exec(emptyArray())).thenReturn(process)
        whenever(process.inputStream).thenReturn(inputStream)
        whenever(process.waitFor()).thenReturn(0)
        console = ConsoleDefault(runtime)
    }

    @Test
    fun `run executes`() {
        val result = console.run()
        assert(result.isSuccess) {
            "Result is expected to be success, instead was ${result.exceptionOrNull()}"
        }
    }

    @Test
    fun `run returns values`() {
        val expectedOutput = listOf("Hello")
        val expectedBytes = expectedOutput
            .joinToString(separator = System.lineSeparator())
            .encodeToByteArray()

        whenever(process.inputStream).thenReturn(ByteArrayInputStream(expectedBytes))

        val result = console.run().getOrNull()

        require(result != null) { "Result is expected to be not null" }

        val lines = result.lines.toList()
        assert(lines.containsAll(expectedOutput)) { "Expected to contain $expectedOutput instead was $lines" }
        assert(expectedOutput.containsAll(lines)) { "Expected to contain $expectedOutput instead was $lines" }
    }

    @Test
    fun `run matches input arguments`() {
        whenever(runtime.exec(arrayOf("foo", "bar"))).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            console.run("foo", "bar")
        }
    }

    @Test
    fun `run returns failure`() {
        whenever(process.waitFor()).thenReturn(1)
        val result = console.run()
        assert(result.isFailure) { "Expected to be failure instead was ${result.getOrThrow()}" }
    }

}