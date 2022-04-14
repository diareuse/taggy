package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import taggy.provider.git.GitService
import taggy.provider.git.Remote
import taggy.provider.git.Tag
import taggy.semver.SemanticTag
import taggy.test.TestSuccessful

internal class TagFacadePushEffectTest {

    private lateinit var expectRemote: Remote
    private lateinit var expectTag: Tag
    private lateinit var expectSemanticTag: SemanticTag
    private lateinit var facade: TagFacadePushEffect

    @Mock
    lateinit var adapter: TagAdapter

    @Mock
    lateinit var git: GitService

    @Mock
    lateinit var source: TagFacade

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        facade = TagFacadePushEffect(source, git, adapter)
        expectSemanticTag = SemanticTag("1.0.0")
        expectTag = Tag("1.0.0")
        expectRemote = Remote("origin")
        whenever(source.getRemotes()).thenReturn(listOf(expectRemote))
        whenever(source.createTag()).thenReturn(expectSemanticTag)
        whenever(adapter.adapt(expectSemanticTag)).thenReturn(expectTag)
        whenever(git.pushTag(expectRemote, expectTag)).thenReturn(Result.success(Unit))
    }

    @Test
    fun createTag() {
        whenever(git.pushTag(expectRemote, expectTag)).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            facade.createTag()
        }
    }

}