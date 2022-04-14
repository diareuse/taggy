package taggy.semver

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

internal class SemanticTagComparatorDefaultTest {

    private lateinit var second: SemanticTag
    private lateinit var first: SemanticTag
    private lateinit var comparator: SemanticTagComparatorDefault

    @Mock
    lateinit var adapter: SemanticSegmentsAdapter

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        comparator = SemanticTagComparatorDefault(adapter)
        first = SemanticTag("first")
        second = SemanticTag("second")
    }

    @Test
    fun `compare resolves first null`() {
        val result = comparator.compare(null, second)
        assert(result < 0) { "Expected result to be < 0 instead was $result" }
    }

    @Test
    fun `compare resolves second null`() {
        val result = comparator.compare(first, null)
        assert(result > 0) { "Expected result to be > 0 instead was $result" }
    }

    @Test
    fun `compare resolves both null`() {
        val result = comparator.compare(null, null)
        assert(result == 0) { "Expected result to be 0 instead was $result" }
    }

    @Test
    fun `compare resolves equal`() {
        val result = comparator.compare(first, first)
        assert(result == 0) { "Expected result to be 0 instead was $result" }
    }

    @Test
    fun `compare resolves greater number of segments`() {
        whenever(adapter.adapt(first)).thenReturn(SemanticSegments(listOf(5, 1, 0), null))
        whenever(adapter.adapt(second)).thenReturn(SemanticSegments(listOf(5), null))
        val result = comparator.compare(first, second)
        assert(result > 0) { "Expected result to be > 0 instead was $result" }
    }

    @Test
    fun `compare resolves higher version`() {
        whenever(adapter.adapt(first)).thenReturn(SemanticSegments(listOf(5, 1, 0), null))
        whenever(adapter.adapt(second)).thenReturn(SemanticSegments(listOf(5, 0, 0), null))
        val result = comparator.compare(first, second)
        assert(result > 0) { "Expected result to be > 0 instead was $result" }
    }

    @Test
    fun `compare resolves smaller number of segments`() {
        whenever(adapter.adapt(first)).thenReturn(SemanticSegments(listOf(5), null))
        whenever(adapter.adapt(second)).thenReturn(SemanticSegments(listOf(5, 1, 0), null))
        val result = comparator.compare(first, second)
        assert(result < 0) { "Expected result to be < 0 instead was $result" }
    }

    @Test
    fun `compare resolves lower version`() {
        whenever(adapter.adapt(first)).thenReturn(SemanticSegments(listOf(5, 0, 0), null))
        whenever(adapter.adapt(second)).thenReturn(SemanticSegments(listOf(5, 1, 0), null))
        val result = comparator.compare(first, second)
        assert(result < 0) { "Expected result to be < 0 instead was $result" }
    }

    @Test
    fun `compare resolves affixed`() {
        whenever(adapter.adapt(first)).thenReturn(SemanticSegments(listOf(5, 1, 0), 5))
        whenever(adapter.adapt(second)).thenReturn(SemanticSegments(listOf(5, 1, 0), null))
        val result = comparator.compare(first, second)
        assert(result < 0) { "Expected result to be < 0 instead was $result" }
    }

    @Test
    fun `compare resolves both affixed`() {
        whenever(adapter.adapt(first)).thenReturn(SemanticSegments(listOf(5, 1, 0), 5))
        whenever(adapter.adapt(second)).thenReturn(SemanticSegments(listOf(5, 1, 0), 1))
        val result = comparator.compare(first, second)
        assert(result > 0) { "Expected result to be > 0 instead was $result" }
    }

}