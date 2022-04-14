@file:JvmName("App")

package taggy

import taggy.startup.Args

fun main(args: Array<String>) {
    val argsBuilder = Args.Builder()
        .build(args)

    AppStarter.Builder()
        .setArgs(argsBuilder)
        .start()
}