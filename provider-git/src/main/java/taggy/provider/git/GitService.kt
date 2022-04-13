package taggy.provider.git

interface GitService {

    // --- repo management

    fun initialize(): Result<Unit>

    // --- remotes

    fun getRemotes(): Result<List<Remote>>

    // --- tags

    fun getTags(): Result<List<Tag>>
    fun createTag(tag: Tag): Result<Unit>
    fun deleteTag(tag: Tag): Result<Unit>
    fun pushTag(remote: Remote, tag: Tag): Result<Unit>

}