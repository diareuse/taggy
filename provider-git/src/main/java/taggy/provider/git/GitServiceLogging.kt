package taggy.provider.git

import java.util.logging.Logger

class GitServiceLogging(
    private val source: GitService,
    private val logger: Logger
) : GitService by source {

    override fun deleteTag(tag: Tag) = source.deleteTag(tag).onSuccess {
        logger.warning("> Deleted tag ${tag.name} locally")
    }

    override fun pushTag(remote: Remote, tag: Tag) = source.pushTag(remote, tag).onSuccess {
        if (tag.name.startsWith(":"))
            logger.warning("> Deleted tag ${tag.name.takeLast(tag.name.length - 1)} from ${remote.name}")
    }

}