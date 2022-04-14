package taggy.semver

interface SemanticTagWrapperFactory {

    fun create(tag: SemanticTag): SemanticTagWrapper

}