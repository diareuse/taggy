package taggy.semver

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SemanticSegmentsAdapterDefaultTest {

    private lateinit var adapter: SemanticSegmentsAdapterDefault

    @BeforeEach
    fun setUp() {
        adapter = SemanticSegmentsAdapterDefault()
    }

    @Test
    fun `adapt splits segments`() {
        val tag = SemanticTag("1.103.50", null, null)
        val expected = listOf(1L, 103L, 50L)
        val result = adapter.adapt(tag)
        assert(result.segments == expected) { "Expected segments to be equal to $expected instead were ${result.segments}" }
    }

    @Test
    fun `adapt extracts revision`() {
        val tag = SemanticTag("1", null, "alpha5")
        val expected = 5L
        val result = adapter.adapt(tag)
        assert(result.revision == expected) { "Expected revision to be equal to $expected instead was ${result.revision}" }
    }

    @Test
    fun `adapt extracts last revision`() {
        val tag = SemanticTag("1", null, "alpha5-test1")
        val expected = 1L
        val result = adapter.adapt(tag)
        assert(result.revision == expected) { "Expected revision to be equal to $expected instead was ${result.revision}" }
    }

}