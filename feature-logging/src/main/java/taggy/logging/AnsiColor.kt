package taggy.logging

interface AnsiColor {

    val tag: String

    enum class Regular(override val tag: String) : AnsiColor {
        Black("$ESC[0;30m"),
        Red("$ESC[0;31m"),
        Green("$ESC[0;32m"),
        Yellow("$ESC[0;33m"),
        Blue("$ESC[0;34m"),
        Purple("$ESC[0;35m"),
        Cyan("$ESC[0;36m"),
        White("$ESC[0;37m"),
    }

    enum class Bold(override val tag: String) : AnsiColor {
        Black("$ESC[1;30m"),
        Red("$ESC[1;31m"),
        Green("$ESC[1;32m"),
        Yellow("$ESC[1;33m"),
        Blue("$ESC[1;34m"),
        Purple("$ESC[1;35m"),
        Cyan("$ESC[1;36m"),
        White("$ESC[1;37m"),
    }

    enum class Underline(override val tag: String) : AnsiColor {
        Black("$ESC[4;30m"),
        Red("$ESC[4;31m"),
        Green("$ESC[4;32m"),
        Yellow("$ESC[4;33m"),
        Blue("$ESC[4;34m"),
        Purple("$ESC[4;35m"),
        Cyan("$ESC[4;36m"),
        White("$ESC[4;37m"),
    }

    enum class Background(override val tag: String) : AnsiColor {
        Black("$ESC[40m"),
        Red("$ESC[41m"),
        Green("$ESC[42m"),
        Yellow("$ESC[43m"),
        Blue("$ESC[44m"),
        Purple("$ESC[45m"),
        Cyan("$ESC[46m"),
        White("$ESC[47m"),
    }

    enum class HighIntensity(override val tag: String) : AnsiColor {
        Black("$ESC[0;90m"),
        Red("$ESC[0;91m"),
        Green("$ESC[0;92m"),
        Yellow("$ESC[0;93m"),
        Blue("$ESC[0;94m"),
        Purple("$ESC[0;95m"),
        Cyan("$ESC[0;96m"),
        White("$ESC[0;97m"),
    }

    enum class BoldHighIntensity(override val tag: String) : AnsiColor {
        Black("$ESC[1;90m"),
        Red("$ESC[1;91m"),
        Green("$ESC[1;92m"),
        Yellow("$ESC[1;93m"),
        Blue("$ESC[1;94m"),
        Purple("$ESC[1;95m"),
        Cyan("$ESC[1;96m"),
        White("$ESC[1;97m"),
    }

    enum class HighIntensityBackground(override val tag: String) : AnsiColor {
        Black("$ESC[0;100m"),
        Red("$ESC[0;101m"),
        Green("$ESC[0;102m"),
        Yellow("$ESC[0;103m"),
        Blue("$ESC[0;104m"),
        Purple("$ESC[0;105m"),
        Cyan("$ESC[0;106m"),
        White("$ESC[0;107m"),
    }

    companion object {

        private const val ESC = "\u001B"
        const val Reset = "$ESC[0m"

    }

}