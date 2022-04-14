package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import taggy.provider.git.Remote

internal class RemoteAdapterDefaultTest {

    private lateinit var adapter: RemoteAdapterDefault

    @BeforeEach
    fun setUp() {
        adapter = RemoteAdapterDefault()
    }

    @Test
    fun adapt() {
        val expected = Remote("origin")
        val result = adapter.adapt("origin")
        assert(result == expected) { "Expected result to be equal to $expected instead was $result" }
    }

}