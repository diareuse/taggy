package taggy.semver

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

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
        assert(current === tag) { "Expected current value to be same instance as $tag (${tag.hashCode()}) instead was $current (${current.hashCode()})" }
    }

    @Test
    fun `next returns incremented name`() {
        whenever(adapter.adapt(tag)).thenReturn(SemanticSegments(listOf(1, 0, 0), null))
        val result = wrapper.next()
        val expected = "1.0.1"
        assert(result.toString() == expected) { "Expected result to be equal to $expected instead was $result" }
    }

    @Test
    fun `next returns incremented revision`() {
        tag = tag.copy(type = "alpha", revision = 1)
        wrapper = SemanticTagWrapperDefault(tag, adapter)
        whenever(adapter.adapt(tag)).thenReturn(SemanticSegments(listOf(1, 0, 0), 1))
        val result = wrapper.next()
        val expected = "1.0.0-alpha2"
        assert(result.toString() == expected) { "Expected result to be equal to $expected instead was $result" }
    }

}