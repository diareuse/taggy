package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import taggy.provider.git.Tag
import taggy.semver.SemanticTag

internal class TagAdapterDefaultTest {

    private lateinit var adapter: TagAdapterDefault
    private val separator = "-"

    @BeforeEach
    fun setUp() {
        adapter = TagAdapterDefault(separator)
    }

    @Test
    fun `adapt string null postfix`() {
        val expected = SemanticTag("1.0.0", separator, null, null)
        val result = adapter.adapt("1.0.0", null)
        assert(result == expected) { "Expected result to be equal to $expected instead was $result" }
    }

    @Test
    fun `adapt string with postfix`() {
        val expected = SemanticTag("1.0.0", separator, "test", 25)
        val result = adapter.adapt("1.0.0", "test25")
        assert(result == expected) { "Expected result to be equal to $expected instead was $result" }
    }

    @Test
    fun `adapt string postfix without revision`() {
        val expected = SemanticTag("1.0.0", separator, "test", null)
        val result = adapter.adapt("1.0.0", "test")
        assert(result == expected) { "Expected result to be equal to $expected instead was $result" }
    }

    @Test
    fun `adapt git tag`() {
        val expected = SemanticTag("1.0.0", separator, "test", 25)
        val result = adapter.adapt(Tag("1.0.0${separator}test25"))
        assert(result == expected) { "Expected result to be equal to $expected instead was $result" }
    }

    @Test
    fun `adapt semantic tag`() {
        val expected = Tag("1.0.0${separator}test25")
        val result = adapter.adapt(SemanticTag("1.0.0", separator, "test", 25))
        assert(result == expected) { "Expected result to be equal to $expected instead was $result" }
    }

}