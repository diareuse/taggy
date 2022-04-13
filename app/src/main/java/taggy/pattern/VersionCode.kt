package taggy.pattern

import taggy.characters
import kotlin.math.max

class VersionCode(
    version: String,
    postfix: String?
) : Comparable<VersionCode> {

    private val segments = version.splitToSequence(".")
        .map { it.replace(characters, "").toInt() }
        .toList()

    private val revision = postfix
        ?.replace(characters, "")
        ?.toIntOrNull()

    override operator fun compareTo(other: VersionCode): Int {
        if (this === other) {
            return 0
        }

        val segmentCount = max(
            segments.size,
            other.segments.size
        )

        // loop through all the segments to establish which of these versions is higher
        for (i in 0 until segmentCount) {
            val my = segments.getOrElse(i) { 0 }
            val their = other.segments.getOrElse(i) { 0 }

            return when {
                my > their -> 1
                my < their -> -1
                else -> continue
            }
        }

        // if the versions are identical, then we check for revisions
        val my = revision ?: Int.MAX_VALUE
        val their = other.revision ?: Int.MAX_VALUE

        return my - their
    }

    override fun equals(other: Any?): Boolean {
        if (other !is VersionCode) return false
        return compareTo(other) == 0
    }

    override fun hashCode(): Int {
        var result = segments.hashCode()
        result = 31 * result + (revision ?: 0)
        return result
    }

}