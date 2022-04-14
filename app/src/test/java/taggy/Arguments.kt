package taggy

import taggy.argument.Arguments

fun arguments(
    postfix: String? = null,
    postfixSeparator: String = "-",
    version: String? = null,
    push: Boolean = false,
    force: Boolean = false,
    everywhere: Boolean = false,
    pushTarget: List<String> = emptyList(),
    pattern: Regex = Regex("")
) = Arguments(
    postfix = postfix,
    postfixSeparator = postfixSeparator,
    version = version,
    push = push,
    force = force,
    everywhere = everywhere,
    pushTarget = pushTarget,
    pattern = pattern
)