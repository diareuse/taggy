package taggy.composition

import taggy.provider.Console
import taggy.provider.ConsoleDefault

class ProviderModuleDefault : ProviderModule {

    override fun getConsole(): Console {
        return ConsoleDefault(getRuntime())
    }

    override fun getRuntime(): Runtime {
        return Runtime.getRuntime()
    }

}