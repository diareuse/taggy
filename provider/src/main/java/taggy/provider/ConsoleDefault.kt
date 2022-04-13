package taggy.provider

class ConsoleDefault(
    private val runtime: Runtime
) : Console {

    override fun run(vararg args: Any): Result<ConsoleOutput> {
        val arguments = Array(args.size) { args[it].toString() }
        return runtime.execAwait(arguments)
            .map { it.asSequence() }
            .map { ConsoleOutput(it) }
    }

}