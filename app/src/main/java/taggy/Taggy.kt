package taggy

class Taggy(private val facade: TagFacade) {

    fun createTag() = facade.createTag()

}