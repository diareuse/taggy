package taggy.composition

import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.HelpFormatter
import taggy.argument.ArgumentsFactory
import taggy.argument.ArgumentsFactoryDefault

class ArgumentsModuleDefault(
    private val options: OptionsModule
) : ArgumentsModule {

    override fun getArgumentsFactory(): ArgumentsFactory = ArgumentsFactoryDefault(
        parser = getCommandLineParser(),
        help = getHelpFormatter(),
        postfix = options.getPostfix(),
        postfixSeparator = options.getPostfixSeparator(),
        version = options.getVersion(),
        push = options.getPush(),
        force = options.getForce(),
        everywhere = options.getEverywhere(),
        pushTarget = options.getPushTarget(),
        pattern = options.getPattern(),
    )

    override fun getCommandLineParser(): CommandLineParser {
        return DefaultParser()
    }

    override fun getHelpFormatter(): HelpFormatter {
        return HelpFormatter()
    }

}