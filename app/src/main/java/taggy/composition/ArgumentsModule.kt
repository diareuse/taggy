package taggy.composition

import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.HelpFormatter
import taggy.argument.ArgumentsFactory

interface ArgumentsModule {

    fun getArgumentsFactory(): ArgumentsFactory
    fun getCommandLineParser(): CommandLineParser
    fun getHelpFormatter(): HelpFormatter

}