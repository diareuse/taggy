package taggy.provider.git

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

internal class GitServiceForceTest {

    private lateinit var service: GitServiceForce

    @Mock
    lateinit var source: GitService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        service = GitServiceForce(source)
    }

    @Test
    fun createTag() {
        val tag = Tag("foo")
        var isDeleted = false
        whenever(source.createTag(tag)).then {
            if (isDeleted) Result.success(Unit)
            else Result.failure(Throwable())
        }
        whenever(source.deleteTag(tag)).then {
            isDeleted = true
            Result.success(Unit)
        }
        val result = service.createTag(tag)
        assert(result.isSuccess) { "Expected to be success instead was $result" }
    }

    @Test
    fun pushTag() {
        val remote = Remote("bar")
        val tag = Tag("foo")
        val tagDeletable = Tag(":foo")
        var isDeleted = false
        whenever(source.pushTag(remote, tag)).then {
            if (isDeleted) Result.success(Unit)
            else Result.failure(Throwable())
        }
        whenever(source.pushTag(remote, tagDeletable)).then {
            isDeleted = true
            Result.success(Unit)
        }
        val result = service.createTag(tag)
        assert(result.isSuccess) { "Expected to be success instead was $result" }
    }

}