package taggy.composition

import org.junit.jupiter.api.Test

internal class CompositorTest {

    @Test
    fun `getTaggy succeeds in creating instance`() {
        Compositor.getTaggy(arrayOf("--pattern", ".*", "-p", "-f"))
    }

}