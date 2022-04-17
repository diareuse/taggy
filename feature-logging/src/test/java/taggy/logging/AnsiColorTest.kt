package taggy.logging

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.expect

internal class AnsiColorTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `starts with escape Regular`() {
        AnsiColor.Regular.values()
            .map { it.tag }
            .forEach {
                expect("\u001b".first()) { it.first() }
            }
    }

    @Test
    fun `starts with escape Bold`() {
        AnsiColor.Bold.values()
            .map { it.tag }
            .forEach {
                expect("\u001b".first()) { it.first() }
            }
    }

    @Test
    fun `starts with escape Underline`() {
        AnsiColor.Underline.values()
            .map { it.tag }
            .forEach {
                expect("\u001b".first()) { it.first() }
            }
    }

    @Test
    fun `starts with escape Background`() {
        AnsiColor.Background.values()
            .map { it.tag }
            .forEach {
                expect("\u001b".first()) { it.first() }
            }
    }

    @Test
    fun `starts with escape HighIntensity`() {
        AnsiColor.HighIntensity.values()
            .map { it.tag }
            .forEach {
                expect("\u001b".first()) { it.first() }
            }
    }

    @Test
    fun `starts with escape BoldHighIntensity`() {
        AnsiColor.BoldHighIntensity.values()
            .map { it.tag }
            .forEach {
                expect("\u001b".first()) { it.first() }
            }
    }

    @Test
    fun `starts with escape HighIntensityBackground`() {
        AnsiColor.HighIntensityBackground.values()
            .map { it.tag }
            .forEach {
                expect("\u001b".first()) { it.first() }
            }
    }

    @Test
    fun `starts with escape Reset`() {
        expect("\u001b".first()) {
            AnsiColor.Reset.first()
        }
    }

    // ---

    @Test
    fun `values do not repeat`() {
        val colors = buildList<AnsiColor> {
            this += AnsiColor.Regular.values()
            this += AnsiColor.Bold.values()
            this += AnsiColor.Underline.values()
            this += AnsiColor.Background.values()
            this += AnsiColor.HighIntensity.values()
            this += AnsiColor.BoldHighIntensity.values()
            this += AnsiColor.HighIntensityBackground.values()
        }.map { it.tag }

        for (color in colors) expect(1) {
            colors.count { it == color }
        }
    }

}