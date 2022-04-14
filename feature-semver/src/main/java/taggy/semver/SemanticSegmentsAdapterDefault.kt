package taggy.semver

class SemanticSegmentsAdapterDefault : SemanticSegmentsAdapter {

    override fun adapt(tag: SemanticTag): SemanticSegments {
        val segments = tag.name.splitToSequence('.').map { it.toLong() }.toList()
        return SemanticSegments(
            segments = segments,
            revision = tag.revision
        )
    }

    private fun adaptRevision(affix: String): Long? {
        return Numbers.findAll(affix).lastOrNull()?.value?.toLongOrNull()
    }

    companion object {

        private val Numbers = Regex("(\\d)+")

    }

}