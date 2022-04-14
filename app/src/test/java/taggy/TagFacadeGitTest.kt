package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import taggy.provider.git.GitService
import taggy.provider.git.Remote
import taggy.provider.git.Tag
import taggy.semver.SemanticTag
import taggy.semver.SemanticTagComparator
import kotlin.test.expect

internal class TagFacadeGitTest {

    private lateinit var expectSemanticTag: SemanticTag

    private lateinit var expectTags: List<Tag>

    private lateinit var expectRemotes: List<Remote>
    private lateinit var facade: TagFacadeGit
    private lateinit var regex: Regex

    @Mock
    lateinit var adapter: TagAdapter

    @Mock
    lateinit var comparator: SemanticTagComparator

    @Mock
    lateinit var git: GitService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        regex = Regex(".*")
        facade = TagFacadeGit(git, comparator, adapter, regex)

        expectRemotes = listOf(Remote("origin"))
        whenever(git.getRemotes()).thenReturn(Result.success(expectRemotes))

        expectTags = listOf(Tag("1.0.0"))
        expectSemanticTag = SemanticTag("1.0.0")
        whenever(git.getTags()).thenReturn(Result.success(expectTags))
        for (tag in expectTags)
            whenever(adapter.adapt(tag)).thenReturn(expectSemanticTag)
    }

    @Test
    fun createTag() {
        expect(expectSemanticTag) {
            facade.createTag()
        }
    }

    @Test
    fun getRemotes() {
        expect(expectRemotes) {
            facade.getRemotes()
        }
    }

}