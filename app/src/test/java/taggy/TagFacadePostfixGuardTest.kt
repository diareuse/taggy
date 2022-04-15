package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import taggy.semver.SemanticTag
import kotlin.test.expect

internal class TagFacadePostfixGuardTest {

    private lateinit var facade: TagFacadePostfixGuard

    @Mock
    lateinit var source: TagFacade

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        facade = TagFacadePostfixGuard(source, arguments(postfix = "beta"))
        whenever(source.createTag()).thenReturn(SemanticTag("1.0.0", type = "alpha", revision = 10))
    }

    @Test
    fun createTag() {
        expect(SemanticTag("1.0.0", type = "beta", revision = null)) {
            facade.createTag()
        }
    }

}