package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import taggy.semver.SemanticTag
import java.util.logging.Logger

internal class TagFacadeLoggingTest {

    private lateinit var facade: TagFacadeLogging

    @Mock
    lateinit var source: TagFacade

    @Mock
    lateinit var logger: Logger

    private val step: String = "Test"

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        facade = TagFacadeLogging(source, logger, step)
        whenever(source.createTag()).thenReturn(SemanticTag("1.0.0"))
    }

    @Test
    fun createTag() {
        facade.createTag()
        verify(logger).info("Step [$step] 1.0.0")
    }

}