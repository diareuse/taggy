package taggy.argument

data class Arguments(
    val postfix: String?,
    val postfixSeparator: String,
    val version: String?,
    val push: Boolean,
    val force: Boolean,
    @Deprecated("")
    val everywhere: Boolean,
    @Deprecated("")
    val pushTarget: List<String>,
    val pattern: Regex
)