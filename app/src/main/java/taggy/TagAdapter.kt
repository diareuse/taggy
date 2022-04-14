package taggy

import taggy.provider.git.Tag
import taggy.semver.SemanticTag

interface TagAdapter {

    fun adapt(version: String, postfix: String?): SemanticTag
    fun adapt(tag: Tag): SemanticTag
    fun adapt(tag: SemanticTag): Tag

}