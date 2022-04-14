package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import taggy.provider.git.Remote
import taggy.semver.SemanticTag
import kotlin.test.expect

internal class TagFacadeRecoverTest {

    private lateinit var facade: TagFacadeRecover

    @Mock
    lateinit var recover: TagFacade

    @Mock
    lateinit var primary: TagFacade


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        facade = TagFacadeRecover(primary, recover)
    }

    @Test
    fun createTag() {
        val expected = SemanticTag("")
        whenever(primary.createTag()).thenThrow(IllegalStateException())
        whenever(recover.createTag()).thenReturn(expected)
        expect(expected) {
            facade.createTag()
        }
    }

    @Test
    fun getRemotes() {
        val expected = listOf(Remote(""))
        whenever(primary.getRemotes()).thenThrow(IllegalStateException())
        whenever(recover.getRemotes()).thenReturn(expected)
        expect(expected) {
            facade.getRemotes()
        }
    }

}