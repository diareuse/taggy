package taggy

import taggy.provider.git.GitService
import taggy.semver.SemanticTagComparator

class TagFacadeGit(
    private val git: GitService,
    private val comparator: SemanticTagComparator,
    private val adapter: TagAdapter,
    private val regex: Regex
) : TagFacade {

    override fun createTag() = git.getTags().getOrThrow()
        .asSequence()
        .filter { regex.matches(it.name) }
        .map(adapter::adapt)
        .sortedWith(comparator)
        .first()

    override fun getRemotes() = git.getRemotes().getOrThrow()

}