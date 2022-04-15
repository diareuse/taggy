package taggy.test

import kotlin.test.assertTrue

fun assertIsAtLeast(expected: Int, actual: Int) =
    assertTrue(actual >= expected, "Expected to be at least <$expected>, actual <$actual>.")

fun assertIsAtLeastExcluding(expected: Int, actual: Int) =
    assertTrue(actual > expected, "Expected to be at least (excluding) <$expected>, actual <$actual>.")

fun assertIsAtMost(expected: Int, actual: Int) =
    assertTrue(actual <= expected, "Expected to be at most <$expected>, actual <$actual>.")

fun assertIsAtMostExcluding(expected: Int, actual: Int) =
    assertTrue(actual < expected, "Expected to be at most (excluding) <$expected>, actual <$actual>.")
