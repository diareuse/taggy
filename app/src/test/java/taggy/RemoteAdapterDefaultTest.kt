package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import taggy.provider.git.Remote
import kotlin.test.expect

internal class RemoteAdapterDefaultTest {

    private lateinit var adapter: RemoteAdapterDefault

    @BeforeEach
    fun setUp() {
        adapter = RemoteAdapterDefault()
    }

    @Test
    fun adapt() {
        expect(Remote("origin")) {
            adapter.adapt("origin")
        }
    }

}