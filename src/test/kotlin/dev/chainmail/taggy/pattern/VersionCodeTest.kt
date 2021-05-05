package dev.chainmail.taggy.pattern

import org.junit.Test
import kotlin.test.assertTrue

class VersionCodeTest {

    private fun testComparison(
        old: String,
        new: String,
        postfix: String? = null,
        separator: String = "-"
    ) {
        val contenderOld = Tag.from(old, postfix, separator)
        val contenderNew = Tag.from(new, postfix, separator)
        assertTrue("Codes do not properly handle semantic versioning. $new is newer than $old") {
            contenderNew.code > contenderOld.code
        }
    }

    @Test
    fun semanticComparison() {
        testComparison(old = "1.193.0", new = "2.0.0")
    }

    @Test
    fun semanticUnilateralPostfixComparison() {
        testComparison(old = "1.193.0-dev1", new = "1.193.0", postfix = "dev")
    }

    @Test
    fun semanticBilateralPostfixComparison() {
        testComparison(old = "1.193.0-dev1", new = "1.193.0-dev2", postfix = "dev")
    }

    @Test
    fun semanticUnilateralStagePostfixComparison() {
        testComparison(old = "1.193.0", new = "1.193.1-dev1", postfix = "dev")
    }

    // ---

    private fun testEquality(
        old: String,
        new: String,
        postfix: String? = null,
        separator: String = "-"
    ) {
        val contenderOld = Tag.from(old, postfix, separator)
        val contenderNew = Tag.from(new, postfix, separator)
        assertTrue("Codes do not properly handle semantic versioning. $new is not equal to $old") {
            contenderNew.code == contenderOld.code
        }
    }

    @Test
    fun semanticEquality() {
        testEquality(old = "1.0.0", new = "1.0.0")
    }

    @Test(expected = AssertionError::class)
    fun semanticFailUnilateralPostfixEquality() {
        testEquality(old = "1.0.0-dev30", new = "1.0.0", postfix = "dev")
    }

    @Test(expected = AssertionError::class)
    fun semanticFailBilateralPostfixEquality() {
        testEquality(old = "1.0.0-dev1", new = "1.0.0-dev2", postfix = "dev")
    }

}