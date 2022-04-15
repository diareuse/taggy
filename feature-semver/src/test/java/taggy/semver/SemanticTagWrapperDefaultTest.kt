package taggy.semver

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertSame
import kotlin.test.expect

internal class SemanticTagWrapperDefaultTest {

    private lateinit var wrapper: SemanticTagWrapperDefault
    private lateinit var tag: SemanticTag

    @Mock
    lateinit var adapter: SemanticSegmentsAdapter

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        tag = SemanticTag("1.0.0")
        wrapper = SemanticTagWrapperDefault(tag, adapter)
    }

    @Test
    fun `current returns same instance`() {
        val current = wrapper.current()
        assertSame(tag, current)
    }

    @Test
    fun `next returns incremented name`() {
        whenever(adapter.adapt(tag)).thenReturn(SemanticSegments(listOf(1, 0, 0), null))
        expect("1.0.1") {
            wrapper.next().asString()
        }
    }

    @Test
    fun `next returns incremented revision`() {
        tag = tag.copy(type = "alpha", revision = 1)
        wrapper = SemanticTagWrapperDefault(tag, adapter)
        whenever(adapter.adapt(tag)).thenReturn(SemanticSegments(listOf(1, 0, 0), 1))
        expect("1.0.0-alpha2") {
            wrapper.next().asString()
        }
    }

}