package taggy

import taggy.argument.Arguments
import taggy.semver.SemanticTag

class TagFacadePostfixGuard(
    private val source: TagFacade,
    private val arguments: Arguments
) : TagFacade by source {

    override fun createTag(): SemanticTag {
        val original = source.createTag()
        val separator = arguments.postfixSeparator
        val postfix = arguments.postfix
        return original.copy(
            separator = separator,
            type = postfix ?: original.type,
            revision = original.revision.takeUnless { postfix != null && postfix != original.type }
        )
    }

}