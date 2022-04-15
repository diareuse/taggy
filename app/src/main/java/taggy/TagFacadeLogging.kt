package taggy

import java.util.logging.Logger

class TagFacadeLogging(
    private val source: TagFacade,
    private val logger: Logger,
    private val step: String
) : TagFacade by source {

    override fun createTag() = source.createTag().also {
        logger.info("Step [$step] ${it.asString()}")
    }

}