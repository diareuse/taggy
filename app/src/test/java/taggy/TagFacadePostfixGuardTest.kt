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
        whenever(source.createTag()).thenReturn(SemanticTag("1.0.0", type = "alpha", revision = 10))
    }

    @Test
    fun `createTag clears revision`() {
        facade = TagFacadePostfixGuard(source, arguments(postfix = "beta"))
        expect(SemanticTag("1.0.0", type = "beta", revision = 0)) {
            facade.createTag()
        }
    }

    @Test
    fun `createTag keeps revision`() {
        facade = TagFacadePostfixGuard(source, arguments())
        expect(SemanticTag("1.0.0", type = "alpha", revision = 10)) {
            facade.createTag()
        }
    }

}