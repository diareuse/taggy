package taggy.composition

import java.util.logging.Logger

interface LogModule {

    fun getLogger(): Logger

}