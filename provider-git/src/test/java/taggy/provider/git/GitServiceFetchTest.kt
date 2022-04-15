package taggy.provider.git

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import taggy.provider.Console
import taggy.provider.ConsoleOutput
import taggy.test.TestSuccessful
import kotlin.test.expect

internal class GitServiceFetchTest {

    private lateinit var service: GitServiceFetch

    @Mock
    lateinit var console: Console

    @Mock
    lateinit var source: GitService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        service = GitServiceFetch(source, console)
    }

    @Test
    fun getTags() {
        whenever(console.run("git", "fetch", "--tags")).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            service.getTags()
        }
    }

    @Test
    fun `getTags calls source`() {
        val expected = Result.success(emptyList<Tag>())
        whenever(console.run("git", "fetch", "--tags")).thenReturn(Result.success(ConsoleOutput(emptySequence())))
        whenever(source.getTags()).thenReturn(expected)

        expect(expected) { service.getTags() }
    }

}