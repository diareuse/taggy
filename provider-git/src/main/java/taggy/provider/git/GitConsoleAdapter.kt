package taggy.provider.git

import taggy.provider.ConsoleOutput

interface GitConsoleAdapter {

    fun remote(console: ConsoleOutput): List<Remote>
    fun tag(console: ConsoleOutput): List<Tag>

}