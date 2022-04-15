package taggy

import taggy.argument.Arguments
import taggy.semver.SemanticTag

class TagFacadePostfixGuard(
    private val source: TagFacade,
    private val arguments: Arguments
) : TagFacade by source {

    override fun createTag(): SemanticTag {
        val original = source.createTag()
        return original.copy(
            separator = arguments.postfixSeparator,
            type = arguments.postfix,
            revision = original.revision.takeUnless { arguments.postfix != original.type }
        )
    }

}