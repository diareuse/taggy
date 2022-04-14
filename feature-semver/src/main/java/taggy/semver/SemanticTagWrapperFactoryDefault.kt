package taggy.semver

class SemanticTagWrapperFactoryDefault(
    private val adapter: SemanticSegmentsAdapter
) : SemanticTagWrapperFactory {

    override fun create(tag: SemanticTag): SemanticTagWrapper {
        return SemanticTagWrapperDefault(tag, adapter)
    }

}