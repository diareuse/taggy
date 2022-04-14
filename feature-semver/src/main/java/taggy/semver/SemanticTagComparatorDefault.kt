package taggy.semver

import kotlin.math.max

class SemanticTagComparatorDefault(
    private val adapter: SemanticSegmentsAdapter
) : SemanticTagComparator {

    override fun compare(self: SemanticTag?, other: SemanticTag?): Int = when {
        self == other -> 0
        self == null -> -1
        other == null -> 1
        else -> compareSegments(adapter.adapt(self), adapter.adapt(other))
    }

    private fun compareSegments(self: SemanticSegments, other: SemanticSegments): Int {
        for (index in 0 until max(self.segments.size, other.segments.size)) {
            val selfSegment = self.segments.getOrNull(index)
            val otherSegment = other.segments.getOrNull(index)
            return when {
                selfSegment == otherSegment -> continue
                selfSegment == null -> -1
                otherSegment == null -> 1
                else -> (selfSegment - otherSegment).toInt()
            }
        }

        val selfRevision = self.revision
        val otherRevision = other.revision
        return when {
            selfRevision == otherRevision -> 0
            selfRevision == null -> 1 // intentional change of sentiment (*)
            otherRevision == null -> -1 // intentional change of sentiment (*)
            else -> selfRevision.compareTo(otherRevision)
        }
        // (*) change of sentiment
        // Revision number signals that the tag has an affix and therefore scores lower than the tag without one
        //
        // Ex.
        // 1.603.5         <- is a production revision
        // 1.603.5-test40  <- is a testing revision
        //
        // From this example we can infer that the production revision should not be surpassed by the testing
    }

}