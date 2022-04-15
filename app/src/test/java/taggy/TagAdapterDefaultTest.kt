package taggy

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import taggy.provider.git.Tag
import taggy.semver.SemanticTag
import kotlin.test.expect

internal class TagAdapterDefaultTest {

    private lateinit var adapter: TagAdapterDefault
    private val separator = "-"

    @BeforeEach
    fun setUp() {
        adapter = TagAdapterDefault(separator)
    }

    @Test
    fun `adapt string null postfix`() {
        expect(SemanticTag("1.0.0", separator, null, null)) {
            adapter.adapt("1.0.0", null)
        }
    }

    @Test
    fun `adapt string with postfix`() {
        expect(SemanticTag("1.0.0", separator, "test", 25)) {
            adapter.adapt("1.0.0", "test25")
        }
    }

    @Test
    fun `adapt string postfix without revision`() {
        expect(SemanticTag("1.0.0", separator, "test", null)) {
            adapter.adapt("1.0.0", "test")
        }
    }

    @Test
    fun `adapt git tag`() {
        expect(SemanticTag("1.0.0", separator, "test", 25)) {
            adapter.adapt(Tag("1.0.0${separator}test25"))
        }
    }

    @Test
    fun `adapt semantic tag`() {
        expect(Tag("1.0.0${separator}test35")) {
            adapter.adapt(SemanticTag("1.0.0", separator, "test", 35))
        }
    }

}