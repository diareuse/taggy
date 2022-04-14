package taggy

import taggy.provider.git.GitService

class TagFacadeCreateEffect(
    private val source: TagFacade,
    private val git: GitService,
    private val adapter: TagAdapter
) : TagFacade by source {

    override fun createTag() = source.createTag().also {
        git.createTag(adapter.adapt(it)).getOrThrow()
    }

}