package taggy.provider

import java.io.IOException

data class ProcessException(val code: Int) : IOException("Process exited with code $code")