package taggy

import taggy.provider.git.GitService

class TagFacadePushEffect(
    private val source: TagFacade,
    private val git: GitService,
    private val adapter: TagAdapter
) : TagFacade by source {

    override fun createTag() = source.createTag().also {
        val tag = adapter.adapt(it)
        for (remote in getRemotes())
            git.pushTag(remote, tag).getOrThrow()
    }

}