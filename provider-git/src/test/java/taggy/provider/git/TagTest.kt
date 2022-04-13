package taggy.provider.git

import org.junit.jupiter.api.Test

internal class TagTest {

    @Test
    fun `inverse is not equal`() {
        val tag = Tag("foo")
        val inverse = tag.inverse()
        assert(inverse != tag) { "Expected to be not equal instead $tag was equal to $inverse" }
    }

    @Test
    fun `inverse adds colon`() {
        val tag = Tag("foo").inverse()
        val expected = ":foo"
        assert(tag.name == expected) { "Expected to be $expected instead was ${tag.name}" }
    }

}