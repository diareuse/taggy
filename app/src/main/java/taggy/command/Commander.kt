package taggy.command

import java.io.IOException

class Commander private constructor(
    private val runtime: Runtime
) {

    @Throws(ProcessException::class)
    fun run(vararg args: String): Sequence<String> {
        val process = runtime.exec(args).apply {
            val code = waitFor()
            if (code != 0) {
                throw ProcessException(code)
            }
        }

        return process.inputStream.bufferedReader().lineSequence()
    }

    class ProcessException(
        val code: Int
    ) : IOException("Process failed with code $code")

    class Builder {

        private lateinit var runtime: Runtime

        fun setRuntime(runtime: Runtime) = apply {
            this.runtime = runtime
        }

        fun build(): Commander {
            return Commander(runtime)
        }

    }

}