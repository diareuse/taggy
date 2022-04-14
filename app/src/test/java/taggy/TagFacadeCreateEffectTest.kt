package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import taggy.provider.git.GitService
import taggy.provider.git.Tag
import taggy.semver.SemanticTag
import taggy.test.TestSuccessful

internal class TagFacadeCreateEffectTest {

    private lateinit var facade: TagFacadeCreateEffect

    @Mock
    lateinit var adapter: TagAdapter

    @Mock
    lateinit var git: GitService

    @Mock
    lateinit var source: TagFacade

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        facade = TagFacadeCreateEffect(source, git, adapter)
        whenever(source.createTag()).thenReturn(SemanticTag(""))
        whenever(adapter.adapt(SemanticTag(""))).thenReturn(Tag(""))
    }

    @Test
    fun createTag() {
        whenever(git.createTag(Tag(""))).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            facade.createTag()
        }
    }

}