package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import taggy.argument.Arguments
import taggy.provider.git.Remote
import taggy.semver.SemanticTag
import kotlin.test.assertContentEquals
import kotlin.test.assertSame

internal class TagFacadeArgumentsTest {

    private lateinit var facade: TagFacadeArguments
    private lateinit var arguments: Arguments

    @Mock
    lateinit var remote: RemoteAdapter

    @Mock
    lateinit var tag: TagAdapter

    private lateinit var expectOrigin: Remote
    private lateinit var expectTag: SemanticTag

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        arguments = arguments(version = "5.2.9", postfix = "alpha3", pushTarget = listOf("origin"))
        expectOrigin = Remote("origin")
        expectTag = SemanticTag("5.2.9", type = "alpha", revision = 3)
        facade = TagFacadeArguments(arguments, tag, remote)

        whenever(remote.adapt("origin")).thenReturn(expectOrigin)
        whenever(tag.adapt(arguments.version!!, arguments.postfix)).thenReturn(expectTag)
    }

    @Test
    fun createTag() {
        assertSame(expectTag, facade.createTag())
    }

    @Test
    fun getRemotes() {
        val expected = listOf(expectOrigin)
        val result = facade.getRemotes()
        assertContentEquals(expected, result)
    }

}