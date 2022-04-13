package taggy.provider.git

class GitServiceForce(
    private val source: GitService
) : GitService by source {

    override fun createTag(tag: Tag): Result<Unit> {
        return source.createTag(tag)
            .onFailure { deleteTag(tag) }
            .recoverCatching { source.createTag(tag).getOrThrow() }
    }

    override fun pushTag(remote: Remote, tag: Tag): Result<Unit> {
        return source.pushTag(remote, tag)
            .onFailure { source.pushTag(remote, tag.inverse()) }
            .recoverCatching { source.pushTag(remote, tag).getOrThrow() }
    }

}