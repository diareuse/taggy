package taggy.provider.git

import taggy.provider.ConsoleOutput

class GitConsoleAdapterDefault : GitConsoleAdapter {

    override fun remote(console: ConsoleOutput) = console.lines
        .map { Remote(it) }
        .toList()

    override fun tag(console: ConsoleOutput) = console.lines
        .map { Tag(it) }
        .toList()

}