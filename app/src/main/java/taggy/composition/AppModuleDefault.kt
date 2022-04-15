package taggy.composition

import taggy.*
import taggy.argument.Arguments

class AppModuleDefault(
    private val arguments: Arguments,
    private val git: GitProviderModule,
    private val semver: SemverModule,
    private val log: LogModule
) : AppModule {

    override fun getRemoteAdapter(): RemoteAdapter {
        return RemoteAdapterDefault()
    }

    override fun getTagAdapter(): TagAdapter {
        return TagAdapterDefault(arguments.postfixSeparator)
    }

    override fun getTagFacade(): TagFacade {
        val adapterTag = getTagAdapter()
        val adapterRemote = getRemoteAdapter()
        val logger = log.getLogger()
        val git = git.getService(arguments)

        var facadeDefault: TagFacade
        facadeDefault = TagFacadeDefault()
        facadeDefault = TagFacadeLogging(facadeDefault, logger, "Default")

        var facadeGit: TagFacade
        facadeGit = TagFacadeGit(git, semver.getTagComparator(), adapterTag, arguments.pattern)
        facadeGit = TagFacadeLogging(facadeGit, logger, "Git")
        facadeGit = TagFacadePostfixGuard(facadeGit, arguments)
        facadeGit = TagFacadeIncrement(facadeGit, semver.getTagWrapper())
        facadeGit = TagFacadeLogging(facadeGit, logger, "Increment")
        facadeGit = TagFacadeRecover(facadeGit, facadeDefault)

        var facade: TagFacade
        facade = TagFacadeArguments(arguments, adapterTag, adapterRemote)
        facade = TagFacadeLogging(facade, logger, "Arguments")
        facade = TagFacadeRecover(facade, facadeGit)
        facade = TagFacadeCreateEffect(facade, git, adapterTag)
        facade = TagFacadeLogging(facade, logger, "Created")
        if (arguments.push) {
            facade = TagFacadePushEffect(facade, git, adapterTag)
            facade = TagFacadeLogging(facade, logger, "Pushed")
        }

        return facade
    }

}