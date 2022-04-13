package taggy.provider.git

class GitServicePush(
    private val source: GitService
) : GitService by source {

    override fun createTag(tag: Tag): Result<Unit> {
        return source.createTag(tag)
            .mapCatching { pushTag(tag).getOrThrow() }
    }

    private fun pushTag(tag: Tag): Result<Unit> {
        val remotes = getRemotes().getOrDefault(emptyList())
        val results = mutableListOf<Result<Unit>>()
        for (remote in remotes) {
            results += pushTag(remote, tag)
        }
        return results.firstOrNull { it.isFailure } ?: Result.success(Unit)
    }

}