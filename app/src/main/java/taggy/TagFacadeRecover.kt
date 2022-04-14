package taggy

class TagFacadeRecover(
    private val primary: TagFacade,
    private val recover: TagFacade
) : TagFacade {

    override fun createTag() = try {
        primary.createTag()
    } catch (e: Throwable) {
        recover.createTag()
    }

    override fun getRemotes() = try {
        primary.getRemotes()
    } catch (e: Throwable) {
        recover.getRemotes()
    }

}