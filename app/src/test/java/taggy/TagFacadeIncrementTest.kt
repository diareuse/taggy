package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import taggy.semver.SemanticTag
import taggy.semver.SemanticTagWrapper
import taggy.semver.SemanticTagWrapperFactory
import kotlin.test.expect

internal class TagFacadeIncrementTest {

    private lateinit var expectNextTag: SemanticTag
    private lateinit var expectTag: SemanticTag
    private lateinit var facade: TagFacadeIncrement

    @Mock
    lateinit var factory: SemanticTagWrapperFactory

    @Mock
    lateinit var source: TagFacade

    @Mock
    lateinit var wrapper: SemanticTagWrapper

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        facade = TagFacadeIncrement(source, factory)
        expectTag = SemanticTag("1.0.0")
        expectNextTag = SemanticTag("1.0.1")
        whenever(source.createTag()).thenReturn(expectTag)
        whenever(factory.create(expectTag)).thenReturn(wrapper)
        whenever(wrapper.next()).thenReturn(expectNextTag)
    }

    @Test
    fun createTag() {
        expect(expectNextTag) {
            facade.createTag()
        }
    }

}