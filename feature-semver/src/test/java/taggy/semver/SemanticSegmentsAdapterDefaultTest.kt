package taggy.semver

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.expect

internal class SemanticSegmentsAdapterDefaultTest {

    private lateinit var adapter: SemanticSegmentsAdapterDefault

    @BeforeEach
    fun setUp() {
        adapter = SemanticSegmentsAdapterDefault()
    }

    @Test
    fun `adapt splits segments`() {
        expect(listOf(1L, 103L, 50L)) {
            adapter.adapt(SemanticTag("1.103.50")).segments
        }
    }

    @Test
    fun `adapt extracts revision`() {
        expect(5L) {
            adapter.adapt(SemanticTag("1", "-", "alpha", 5L)).revision
        }
    }

    @Test
    fun `adapt extracts last revision`() {
        expect(1L) {
            adapter.adapt(SemanticTag("1", "-", "alpha5-test", 1L)).revision
        }
    }

    @Test
    fun `adapt adds zero revision when supplied with type`() {
        expect(0L) {
            adapter.adapt(SemanticTag("1", "-", "alpha", null)).revision
        }
    }

}