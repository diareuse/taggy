package taggy.provider.git.tooling

import taggy.provider.git.GitService
import taggy.provider.git.Remote
import taggy.provider.git.Tag

class GitServiceThrowsUnlessDeleted(service: GitService) : GitService by service {

    private val deletedTags = mutableSetOf<String>()
    override fun createTag(tag: Tag): Result<Unit> {
        if (tag.name in deletedTags)
            return Result.success(Unit)
        return Result.failure(Throwable())
    }

    override fun deleteTag(tag: Tag): Result<Unit> {
        deletedTags += tag.name
        return Result.success(Unit)
    }

    private val deletedRemoteTags = mutableSetOf<String>()
    override fun pushTag(remote: Remote, tag: Tag): Result<Unit> {
        if (!tag.name.startsWith(":") && tag.name in deletedRemoteTags)
            return Result.success(Unit)
        return Result.failure(Throwable())
    }

}