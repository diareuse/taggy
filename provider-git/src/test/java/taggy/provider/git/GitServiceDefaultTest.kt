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
import kotlin.test.assertContentEquals

internal class GitServiceDefaultTest {

    private lateinit var service: GitServiceDefault
    private lateinit var output: ConsoleOutput

    @Mock
    lateinit var console: Console

    @Mock
    lateinit var adapter: GitConsoleAdapter

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        output = ConsoleOutput(sequenceOf(""))
        whenever(adapter.remote(output)).thenReturn(listOf(Remote("")))
        whenever(adapter.tag(output)).thenReturn(listOf(Tag("")))
        service = GitServiceDefault(console, adapter)
    }

    @Test
    fun initialize() {
        whenever(console.run("git", "init")).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            service.initialize()
        }
    }

    @Test
    fun getRemotes() {
        whenever(console.run("git", "remote")).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            service.getRemotes()
        }
    }

    @Test
    fun `getRemotes is successful`() {
        val expected = listOf(Remote("foo"), Remote("bar"))
        whenever(console.run("git", "remote")).thenReturn(Result.success(output))
        whenever(adapter.remote(output)).thenReturn(expected)

        val list = service.getRemotes().getOrThrow()
        assertContentEquals(expected, list)
    }

    @Test
    fun getTags() {
        whenever(console.run("git", "tag", "-l")).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            service.getTags()
        }
    }

    @Test
    fun `getTags returns tag`() {
        val expected = listOf(Tag("foo"), Tag("bar"))
        whenever(console.run("git", "tag", "-l")).thenReturn(Result.success(output))
        whenever(adapter.tag(output)).thenReturn(expected)

        val list = service.getTags().getOrThrow()
        assertContentEquals(expected, list)
    }

    @Test
    fun createTag() {
        val tag = Tag("1.0.0")
        whenever(console.run("git", "tag", "-a", "1.0.0", "-m", "via github.com/diareuse/taggy"))
            .thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            service.createTag(tag)
        }
    }

    @Test
    fun deleteTag() {
        val tag = Tag("1.0.0")
        whenever(console.run("git", "tag", "--delete", "1.0.0")).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            service.deleteTag(tag)
        }
    }

    @Test
    fun pushTag() {
        val remote = Remote("origin")
        val tag = Tag("1.0.0")
        whenever(console.run("git", "push", "origin", "1.0.0")).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            service.pushTag(remote, tag)
        }
    }

}