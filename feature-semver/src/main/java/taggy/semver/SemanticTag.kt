package taggy.semver

data class SemanticTag(
    val name: String,
    val separator: String = "-",
    val type: String? = null,
    val revision: Long? = null
) {

    init {
        if (revision != null && type == null)
            throw IllegalStateException("${toString()} has revision, but not type")
    }

    fun asString() = buildString {
        append(name)

        if (revision == null)
            return@buildString

        append(separator)
        append(type)
        append(revision)
    }

}
