package taggy.composition

import taggy.argument.Arguments
import taggy.provider.git.GitConsoleAdapter
import taggy.provider.git.GitService

interface GitProviderModule {

    fun getConsoleAdapter(): GitConsoleAdapter
    fun getService(arguments: Arguments): GitService

}