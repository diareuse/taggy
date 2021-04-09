import dev.chainmail.taggy.App
import dev.chainmail.taggy.startup.Args

fun main(args: Array<String>) {
    val argsBuilder = Args.Builder()
        .build(args)

    App.Builder()
        .setArgs(argsBuilder)
        .start()
}