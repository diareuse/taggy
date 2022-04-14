package taggy

import taggy.provider.git.Remote
import taggy.semver.SemanticTag

class TagFacadeDefault : TagFacade {

    override fun createTag(): SemanticTag {
        return SemanticTag("0.0.1")
    }

    override fun getRemotes(): List<Remote> {
        return emptyList()
    }

}