package taggy.provider.git

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

internal class GitServicePushTest {

    private lateinit var service: GitServicePush
    private lateinit var remote: Remote

    @Mock
    lateinit var source: GitService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        service = GitServicePush(source)
        remote = Remote("barfoo")
        whenever(source.getRemotes()).thenReturn(Result.success(listOf(remote)))
    }

    @Test
    fun createTag() {
        val tag = Tag("foo")
        whenever(source.pushTag(remote, tag)).thenReturn(Result.success(Unit))

        val result = service.createTag(tag)
        assert(result.isSuccess) { "Expected to be success instead was $result" }
    }

}