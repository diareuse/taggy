package taggy.provider.git

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import java.util.logging.Logger

internal class GitServiceLoggingTest {

    private lateinit var service: GitServiceLogging

    @Mock
    lateinit var source: GitService

    @Mock
    lateinit var logger: Logger

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        service = GitServiceLogging(source, logger)
    }

    @Test
    fun deleteTag() {
        service.deleteTag(Tag("1.0.0"))
        verify(source).deleteTag(Tag("1.0.0"))
        verify(logger).warning("> Deleted tag 1.0.0 locally")
    }

    @Test
    fun `pushTag does not log on normal tags`() {
        service.pushTag(Remote("origin"), Tag("1.0.0"))
        verify(source).pushTag(Remote("origin"), Tag("1.0.0"))
    }

    @Test
    fun `pushTag logs on deletions`() {
        service.pushTag(Remote("origin"), Tag(":1.0.0"))
        verify(source).pushTag(Remote("origin"), Tag(":1.0.0"))
        verify(logger).warning("> Deleted tag 1.0.0 from origin")
    }

}