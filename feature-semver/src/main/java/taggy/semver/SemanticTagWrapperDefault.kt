package taggy.semver

class SemanticTagWrapperDefault(
    private val tag: SemanticTag,
    private val adapter: SemanticSegmentsAdapter
) : SemanticTagWrapper {

    override fun current(): SemanticTag {
        return tag
    }

    override fun next(): SemanticTag {
        val segments = adapter.adapt(tag)
        if (segments.revision != null)
            return construct(segments.copy(revision = segments.revision + 1))

        return construct(segments.copy(segments = increment(segments.segments)))
    }

    private fun construct(segments: SemanticSegments): SemanticTag {
        return tag.copy(
            name = segments.segments.joinToString(separator = "."),
            revision = segments.revision
        )
    }

    private fun increment(segments: List<Long>): List<Long> {
        return segments.toMutableList().apply {
            this[lastIndex]++
        }
    }

}