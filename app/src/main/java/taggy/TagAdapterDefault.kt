package taggy

import taggy.provider.git.Tag
import taggy.semver.SemanticTag

class TagAdapterDefault(
    private val separator: String
) : TagAdapter {

    override fun adapt(version: String, postfix: String?): SemanticTag {
        val revision = postfix?.let(::adaptRevision)
        val affix = if (revision != null) {
            postfix.substring(0 until postfix.lastIndexOf(revision.toString()))
        } else {
            postfix
        }
        return SemanticTag(version, separator, affix, revision)
    }

    override fun adapt(tag: Tag): SemanticTag {
        val parts = tag.name.split(separator)
        return adapt(parts[0], parts.takeLast(parts.size - 1).joinToString(separator))
    }

    override fun adapt(tag: SemanticTag): Tag {
        return Tag(tag.asString())
    }

    private fun adaptRevision(affix: String): Long? {
        return Numbers.findAll(affix).lastOrNull()?.value?.toLongOrNull()
    }

    companion object {

        private val Numbers = Regex("(\\d)+")

    }

}