package taggy.provider.git

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import taggy.provider.ConsoleOutput
import kotlin.test.assertContentEquals

internal class GitConsoleAdapterDefaultTest {

    private lateinit var adapter: GitConsoleAdapterDefault

    @BeforeEach
    fun setUp() {
        adapter = GitConsoleAdapterDefault()
    }

    @Test
    fun remote() {
        val output = ConsoleOutput(sequenceOf("origin", "digitalocean"))
        val expected = listOf(Remote("origin"), Remote("digitalocean"))
        val remotes = adapter.remote(output)
        assertContentEquals(expected, remotes)
    }

    @Test
    fun tag() {
        val output = ConsoleOutput(sequenceOf("1.0.0", "1.0.0-alpha1"))
        val expected = listOf(Tag("1.0.0"), Tag("1.0.0-alpha1"))
        val remotes = adapter.tag(output)
        assertContentEquals(expected, remotes)
    }

}