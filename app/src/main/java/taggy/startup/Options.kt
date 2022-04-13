package taggy.startup

import org.apache.commons.cli.Option

class OptionWithDefault<T>(
    val option: Option,
    val default: T,
    val parser: (String) -> T
)

fun <T> Option.withDefault(default: T, parser: (String) -> T) =
    OptionWithDefault(this, default, parser)

fun Option.withDefault(default: Int) = OptionWithDefault(this, default) { it.toInt() }
fun Option.withDefault(default: String) = OptionWithDefault(this, default) { it }
fun Option.withDefaultNull(default: String? = null) = OptionWithDefault(this, default) { it }

class OptionAsFlag(
    val option: Option
)

fun Option.withFlag() = OptionAsFlag(this)

class OptionAsRegex(
    val option: Option
)

fun Option.withRegex() = OptionAsRegex(this)