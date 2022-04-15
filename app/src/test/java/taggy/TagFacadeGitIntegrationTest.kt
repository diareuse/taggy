package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import taggy.provider.git.GitService
import taggy.provider.git.Tag
import taggy.semver.SemanticSegmentsAdapterDefault
import taggy.semver.SemanticTag
import taggy.semver.SemanticTagComparatorDefault
import kotlin.test.expect

internal class TagFacadeGitIntegrationTest {

    private lateinit var expectTags: List<Tag>

    private lateinit var facade: TagFacadeGit
    private lateinit var regex: Regex

    @Mock
    lateinit var git: GitService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        regex = Regex(".*")
        facade = TagFacadeGit(
            git,
            SemanticTagComparatorDefault(SemanticSegmentsAdapterDefault()),
            TagAdapterDefault("-"),
            regex
        )

        expectTags = listOf(Tag("1.0.0"), Tag("2.1.3"), Tag("1.0.1"))
        whenever(git.getTags()).thenReturn(Result.success(expectTags))
    }

    @Test
    fun createTag() {
        expect(SemanticTag("2.1.3")) {
            facade.createTag()
        }
    }

}