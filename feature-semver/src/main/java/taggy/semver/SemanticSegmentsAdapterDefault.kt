package taggy.semver

class SemanticSegmentsAdapterDefault : SemanticSegmentsAdapter {

    override fun adapt(tag: SemanticTag): SemanticSegments {
        val segments = tag.name.splitToSequence('.').map { it.toLong() }.toList()
        return SemanticSegments(
            segments = segments,
            revision = when (tag.type) {
                null -> null
                else -> tag.revision ?: 0
            }
        )
    }

}