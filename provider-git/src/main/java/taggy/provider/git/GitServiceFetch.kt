package taggy.provider.git

import taggy.provider.Console

class GitServiceFetch(
    private val source: GitService,
    private val console: Console
) : GitService by source {

    override fun getTags(): Result<List<Tag>> {
        console.run("git", "fetch", "--tags")
        return source.getTags()
    }

}