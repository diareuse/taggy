package taggy.composition

import taggy.Taggy

object Compositor {

    fun getTaggy(args: Array<out String>): Taggy {
        val options = OptionsModuleDefault()
        val arguments = ArgumentsModuleDefault(options).getArgumentsFactory().create(args)
        val provider = ProviderModuleDefault()
        val log = LogModuleDefault()
        val git = GitProviderModuleDefault(provider, log)
        val semver = SemverModuleDefault()
        val app = AppModuleDefault(arguments, git, semver, log)
        return Taggy(app.getTagFacade())
    }

}