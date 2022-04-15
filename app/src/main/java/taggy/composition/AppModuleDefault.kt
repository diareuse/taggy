package taggy.composition

import taggy.*
import taggy.argument.Arguments

class AppModuleDefault(
    private val arguments: Arguments,
    private val git: GitProviderModule,
    private val semver: SemverModule
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
        val git = git.getService(arguments)

        val facadeDefault: TagFacade
        facadeDefault = TagFacadeDefault()

        var facadeGit: TagFacade
        facadeGit = TagFacadeGit(git, semver.getTagComparator(), adapterTag, arguments.pattern)
        facadeGit = TagFacadeIncrement(facadeGit, semver.getTagWrapper())
        facadeGit = TagFacadeRecover(facadeGit, facadeDefault)

        var facade: TagFacade
        facade = TagFacadeArguments(arguments, adapterTag, adapterRemote)
        facade = TagFacadeRecover(facade, facadeGit)
        facade = TagFacadeCreateEffect(facade, git, adapterTag)
        if (arguments.push)
            facade = TagFacadePushEffect(facade, git, adapterTag)

        return facade
    }

}