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
import kotlin.test.assertContentEquals
import kotlin.test.assertNotNull
import kotlin.test.expect

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
    fun `run executes without input`() {
        expect(Result.success(emptyList())) {
            console.run().map { it.lines.toList() }
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

        assertNotNull(result)
        assertContentEquals(expectedOutput, result.lines.toList())
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
        expect(Result.failure(ProcessException(1))) {
            console.run()
        }
    }

}