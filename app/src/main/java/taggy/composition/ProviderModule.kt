package taggy.composition

import taggy.provider.Console

interface ProviderModule {

    fun getConsole(): Console
    fun getRuntime(): Runtime

}