package taggy.semver

data class SemanticTag(
    val name: String,
    val separator: String? = null,
    val affix: String? = null
) {

    override fun toString() = buildString {
        append(name)

        if (affix == null)
            return@buildString
        if (separator == null)
            throw IllegalStateException("${super.toString()} has an affix, but not a separator")

        append(separator)
        append(affix)
    }

}
