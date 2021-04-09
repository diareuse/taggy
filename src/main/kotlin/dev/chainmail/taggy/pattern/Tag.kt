package dev.chainmail.taggy.pattern

@Suppress("DataClassPrivateConstructor")
data class Tag(
    val name: String,
    val postfix: String?,
    val separator: String
) {

    val code: Int = name.splitToSequence('.')
        .map { it.replace(characters, "").toInt() }
        .reduce { acc, it -> acc * 100 + it }

    operator fun inc(): Tag {

        fun incName(): String {
            val tokens = name
                .splitToSequence('.')
                .map { it.toInt() }
                .toMutableList()

            tokens[tokens.size - 1]++

            return tokens.joinToString(".") { it.toString() }
        }

        if (postfix != null) {
            return if (postfix.contains(numbers)) {
                val number = postfix.replace(characters, "").toInt()
                val chars = postfix.replace(numbers, "")
                copy(postfix = "${chars}${number + 1}")
            } else {
                copy(
                    name = incName(),
                    postfix = "${postfix}1"
                )
            }
        }

        return copy(
            name = incName()
        )
    }

    override fun toString(): String {
        return StringBuilder()
            .append(name)
            .appendIf(postfix != null) {
                append(separator)
                append(postfix)
            }
            .toString()
    }

    companion object {

        private val characters = Regex("[a-zA-Z]+")
        private val numbers = Regex("[0-9]+")

        fun from(tag: String, postfix: String?, separator: String): Tag {
            if (
                tag.contains(separator) &&
                (postfix == null || tag.contains(postfix))
            ) {
                val tokens = tag.split(separator)
                return Tag(
                    name = tokens[0],
                    postfix = tokens[1],
                    separator = separator
                )
            }
            return Tag(
                name = tag.substringBefore(separator),
                postfix = postfix,
                separator = separator
            )
        }

    }

}

private inline fun StringBuilder.appendIf(condition: Boolean, appendable: StringBuilder.() -> Unit) = apply {
    if (condition) {
        appendable()
    }
}
