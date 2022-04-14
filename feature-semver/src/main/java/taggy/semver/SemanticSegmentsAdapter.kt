package taggy.semver

interface SemanticSegmentsAdapter {

    fun adapt(tag: SemanticTag): SemanticSegments

}