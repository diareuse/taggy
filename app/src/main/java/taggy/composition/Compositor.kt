package taggy.composition

import taggy.Taggy

object Compositor {

    fun getTaggy(args: Array<out String>): Taggy {
        val options = OptionsModuleDefault()
        val arguments = ArgumentsModuleDefault(options).getArgumentsFactory().create(args)
        val provider = ProviderModuleDefault()
        val git = GitProviderModuleDefault(provider)
        val semver = SemverModuleDefault()
        val log = LogModuleDefault()
        val app = AppModuleDefault(arguments, git, semver, log)
        return Taggy(app.getTagFacade())
    }

}