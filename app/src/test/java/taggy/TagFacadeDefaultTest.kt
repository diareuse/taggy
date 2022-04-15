package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import taggy.provider.git.Remote
import taggy.semver.SemanticTag
import kotlin.test.assertSame
import kotlin.test.expect

internal class TagFacadeDefaultTest {

    private lateinit var facade: TagFacadeDefault

    @BeforeEach
    fun setUp() {
        facade = TagFacadeDefault()
    }

    @Test
    fun createTag() {
        expect(SemanticTag("0.0.1")) {
            facade.createTag()
        }
    }

    @Test
    fun getRemotes() {
        val expected = emptyList<Remote>()
        val result = facade.getRemotes()
        assertSame(expected, result)
    }

}