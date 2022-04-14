@file:JvmName("App")

package taggy

import taggy.composition.*

fun main(args: Array<String>) {
    val options = OptionsModuleDefault()
    val arguments = ArgumentsModuleDefault(options)
    val provider = ProviderModuleDefault()
    val gitProvider = GitProviderModuleDefault(provider)
    val semver = SemverModuleDefault()

    val argus = arguments.getArgumentsFactory().create(args)
    val service = gitProvider.getService(argus)

}