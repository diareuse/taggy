package taggy.semver

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class SemanticTagWrapperFactoryDefaultTest {

    private lateinit var factory: SemanticTagWrapperFactoryDefault

    @Mock
    lateinit var adapter: SemanticSegmentsAdapter

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        factory = SemanticTagWrapperFactoryDefault(adapter)
    }

    @Test
    fun create() {
        val tag = SemanticTag("")
        val wrapper = factory.create(tag)
        assert(wrapper.current() === tag) { "Expected tag to be $tag instead was ${wrapper.current()}" }
    }

}