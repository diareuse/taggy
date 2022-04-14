package taggy.semver

interface SemanticTagWrapper {

    fun current(): SemanticTag
    fun next(): SemanticTag

}