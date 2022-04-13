package taggy.provider.git

import taggy.provider.Console

class GitServiceDefault(
    private val console: Console,
    private val adapter: GitConsoleAdapter
) : GitService {

    override fun initialize(): Result<Unit> {
        return console.run("git", "init")
            .map {}
    }

    override fun getRemotes(): Result<List<Remote>> {
        return console.run("git", "remote")
            .map(adapter::remote)
    }

    override fun getTags(): Result<List<Tag>> {
        return console.run("git", "tag", "-l")
            .map(adapter::tag)
    }

    override fun createTag(tag: Tag): Result<Unit> {
        return console.run("git", "tag", "-a", tag.name, "-m", "via github.com/diareuse/taggy")
            .map {}
    }

    override fun deleteTag(tag: Tag): Result<Unit> {
        return console.run("git", "tag", "--delete", tag.name)
            .map {}
    }

    override fun pushTag(remote: Remote, tag: Tag): Result<Unit> {
        return console.run("git", "push", remote.name, tag.name)
            .map {}
    }

}