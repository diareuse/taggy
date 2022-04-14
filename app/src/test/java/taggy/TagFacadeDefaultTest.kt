package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import taggy.provider.git.Remote
import taggy.semver.SemanticTag

internal class TagFacadeDefaultTest {

    private lateinit var facade: TagFacadeDefault

    @BeforeEach
    fun setUp() {
        facade = TagFacadeDefault()
    }

    @Test
    fun createTag() {
        val expected = SemanticTag("0.0.1")
        val result = facade.createTag()
        assert(expected == result) { "Expected to be equal to $expected instead was $result" }
    }

    @Test
    fun getRemotes() {
        val expected = emptyList<Remote>()
        val result = facade.getRemotes()
        assert(expected === result) { "Expected to be same instance as $expected (${expected.hashCode()}) instead was $result (${result.hashCode()})" }
    }

}