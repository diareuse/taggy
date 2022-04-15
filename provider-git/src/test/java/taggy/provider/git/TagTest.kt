package taggy.provider.git

import org.junit.jupiter.api.Test
import kotlin.test.assertNotSame
import kotlin.test.expect

internal class TagTest {

    @Test
    fun `inverse is not equal`() {
        val tag = Tag("foo")
        val inverse = tag.inverse()
        assertNotSame(tag, inverse)
    }

    @Test
    fun `inverse adds colon`() {
        val tag = Tag("foo").inverse()
        val expected = ":foo"
        expect(expected) { tag.name }
    }

}