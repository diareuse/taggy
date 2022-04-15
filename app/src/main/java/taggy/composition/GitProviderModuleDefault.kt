package taggy.composition

import taggy.argument.Arguments
import taggy.provider.git.*

class GitProviderModuleDefault(
    private val provider: ProviderModule,
    private val log: LogModule
) : GitProviderModule {

    override fun getConsoleAdapter(): GitConsoleAdapter {
        return GitConsoleAdapterDefault()
    }

    override fun getService(arguments: Arguments): GitService {
        val console = provider.getConsole()
        var service: GitService
        service = GitServiceDefault(console, getConsoleAdapter())
        service = GitServiceFetch(service, console)
        if (arguments.push)
            service = GitServicePush(service)
        if (arguments.force)
            service = GitServiceForce(service)
        service = GitServiceLogging(service, log.getLogger())
        return service
    }

}