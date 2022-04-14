package taggy

import taggy.semver.SemanticTag
import taggy.semver.SemanticTagWrapperFactory

class TagFacadeIncrement(
    private val source: TagFacade,
    private val wrapper: SemanticTagWrapperFactory
) : TagFacade by source {

    override fun createTag(): SemanticTag {
        val original = source.createTag()
        val wrapper = wrapper.create(original)
        return wrapper.next()
    }

}