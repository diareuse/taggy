package taggy.provider

interface Console {

    fun run(vararg args: Any): Result<ConsoleOutput>

}
