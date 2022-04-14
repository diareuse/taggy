package taggy.argument

import org.apache.commons.cli.*
import kotlin.system.exitProcess

class ArgumentsFactoryDefault(
    private val parser: CommandLineParser,
    private val help: HelpFormatter,
    private val postfix: Option,
    private val postfixSeparator: Option,
    private val version: Option,
    private val push: Option,
    private val force: Option,
    @Deprecated("")
    private val everywhere: Option,
    @Deprecated("")
    private val pushTarget: Option,
    private val pattern: Option
) : ArgumentsFactory {

    private val options = Options().apply {
        addOption(postfix)
        addOption(postfixSeparator)
        addOption(version)
        addOption(push)
        addOption(force)
        addOption(everywhere)
        addOption(pushTarget)
        addOption(pattern)
    }

    override fun create(arguments: Array<out String>): Arguments {
        val cli = try {
            parser.parse(options, arguments)
        } catch (e: UnrecognizedOptionException) {
            help.printHelp("taggy", options, true)
            exitProcess(0)
        }
        return Arguments(
            postfix = cli.getOptionValue(postfix),
            postfixSeparator = cli.getOptionValue(postfixSeparator) ?: "-",
            version = cli.getOptionValue(version),
            push = cli.hasOption(push),
            force = cli.hasOption(force),
            everywhere = cli.hasOption(everywhere),
            pushTarget = cli.getOptionValues(pushTarget)?.toList().orEmpty(),
            pattern = Regex(cli.getOptionValue(pattern).let(::requireNotNull))
        )
    }

}