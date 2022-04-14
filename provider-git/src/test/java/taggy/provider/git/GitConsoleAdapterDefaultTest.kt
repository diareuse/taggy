package taggy.provider.git

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import taggy.provider.ConsoleOutput

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
        assert(expected.containsAll(remotes)) { "Expected to contain all elements from $expected instead were $remotes" }
        assert(remotes.containsAll(expected)) { "Expected to contain all elements from $expected instead were $remotes" }
    }

    @Test
    fun tag() {
        val output = ConsoleOutput(sequenceOf("1.0.0", "1.0.0-alpha1"))
        val expected = listOf(Tag("1.0.0"), Tag("1.0.0-alpha1"))
        val remotes = adapter.tag(output)
        assert(expected.containsAll(remotes)) { "Expected to contain all elements from $expected instead were $remotes" }
        assert(remotes.containsAll(expected)) { "Expected to contain all elements from $expected instead were $remotes" }
    }

}