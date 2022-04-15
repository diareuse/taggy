package taggy.provider

internal fun Runtime.execAwait(args: Array<out String>): Result<Process> {
    val process = exec(args)
    val code = process.waitFor()
    if (code != 0) {
        return Result.failure(ProcessException(code))
    }

    return Result.success(process)
}

internal fun Process.asSequence() = sequence {
    inputStream.bufferedReader().use {
        if (!it.ready()) return@sequence
        var line: String? = it.readLine()
        while (line != null) {
            yield(line)
            line = it.readLine()
        }
    }
}