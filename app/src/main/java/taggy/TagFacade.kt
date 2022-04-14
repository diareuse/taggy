package taggy

import taggy.provider.git.Remote
import taggy.semver.SemanticTag

interface TagFacade {

    fun createTag(): SemanticTag
    fun getRemotes(): List<Remote>

}