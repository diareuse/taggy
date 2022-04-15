package taggy.provider.git

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import taggy.provider.git.tooling.GitServiceThrowsUnlessDeleted
import kotlin.test.expect

internal class GitServiceForceTest {

    private lateinit var service: GitServiceForce

    @Mock
    lateinit var source: GitService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        service = GitServiceForce(GitServiceThrowsUnlessDeleted(source))
    }

    @Test
    fun createTag() {
        val tag = Tag("foo")
        expect(Result.success(Unit)) { service.createTag(tag) }
    }

    @Test
    fun pushTag() {
        val tag = Tag("foo")
        expect(Result.success(Unit)) { service.createTag(tag) }
    }

}