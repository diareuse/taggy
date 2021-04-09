package dev.chainmail.taggy.startup

import dev.chainmail.taggy.process.ProcessStatus
import org.apache.commons.cli.*
import kotlin.reflect.KProperty
import kotlin.system.exitProcess

class Args(private val options: CommandLine) {

    val postfix by Args.postfix.withDefaultNull()
    val postfixSeparator by Args.postfixSeparator.withDefault("-")

    val version by Args.version.withDefaultNull()

    val push by Args.push.withFlag()
    val force by Args.force.withFlag()

    val pushTarget by Args.pushTarget.withDefaultNull()

    val pattern by Args.pattern.withRegex()

    private operator fun <T> OptionWithDefault<T>.getValue(args: Args, property: KProperty<*>): T {
        val value = args.options.getOptionValue(option.opt ?: option.longOpt, default?.toString())
        return value?.let(parser) as T
    }

    private operator fun OptionAsFlag.getValue(args: Args, property: KProperty<*>): Boolean {
        val value = args.options.hasOption(option.opt ?: option.longOpt)
        return value
    }

    private operator fun OptionAsRegex.getValue(args: Args, property: KProperty<*>): Regex {
        val value = args.options.getOptionValue(option.opt ?: option.longOpt)
        return Regex(value)
    }


    companion object {

        private val postfix: Option = Option.builder("a")
            .longOpt("postfix")
            .required(false)
            .hasArg()
            .desc("Appends postfix to the latest fetched of provided version. If not provided the program will try to reuse existing postfix from the last tag. If even this last tag has no postfixes it will be omitted. Increment is always preferred on postfix, however if no postfix is found or provided the last number segment from the tag is incremented.")
            .build()

        private val postfixSeparator: Option = Option.builder()
            .longOpt("postfix-separator")
            .required(false)
            .hasArg()
            .desc(
                "Separator which will be used for parsing appended postfix and appending the postfix when constructing tag. Beware that if you want to change separator, you need to provide the entire ensemble. (ie. postfix, postfix-separator and version)"
            )
            .build()

        private val version: Option = Option.builder("v")
            .longOpt("version")
            .required(false)
            .hasArg()
            .desc("Program will use this version code without any increments in order to construct new tag. Note that if postfix was provided it will use the postfix as well in the tag construction process.")
            .build()

        private val push: Option = Option.builder("p")
            .longOpt("push")
            .required(false)
            .desc("Informs the program that you want to also push the tag you created. This is by default to all remotes. You can limit remotes by using -t or --push-targets.")
            .build()

        private val pushTarget: Option = Option.builder("t")
            .longOpt("push-targets")
            .numberOfArgs(Option.UNLIMITED_VALUES)
            .hasArgs()
            .required(false)
            .desc("Specifies which targets are used in order to push the newly created tags. When used -p and -t in conjunction, the targets must not be empty.")
            .build()

        private val force: Option = Option.builder("f")
            .longOpt("force")
            .required(false)
            .desc("Forces all tags on all remotes to be deleted and created again. Tags will be deleted locally and on the conflicting remote.")
            .build()

        private val pattern: Option = Option.builder()
            .longOpt("pattern")
            .hasArg()
            .optionalArg(false)
            .required(true)
            .desc("Specifies java regex pattern for the program to use when listing tags. It bypasses git's implementation since it uses non-robust wildcard implementation. This parameter is required.")
            .build()

    }


    class Builder {

        private val options = Options().apply {
            addOption(postfix)
            addOption(postfixSeparator)
            addOption(version)
            addOption(push)
            addOption(pushTarget)
            addOption(force)
            addOption(pattern)
        }
        private var help: HelpFormatter = HelpFormatter()
        private var parser: CommandLineParser = DefaultParser()

        fun addOption(option: Option) = apply {
            options.addOption(option)
        }

        fun setHelpFormatter(help: HelpFormatter) = apply {
            this.help = help
        }

        fun setParser(parser: CommandLineParser) = apply {
            this.parser = parser
        }

        fun build(args: Array<out String>): Args {
            val commandLine = try {
                parser.parse(options, args)
            } catch (e: UnrecognizedOptionException) {
                help.printHelp(e.option, options)
                exitProcess(0)
            } catch (e: ParseException) {
                e.printStackTrace()
                exitProcess(ProcessStatus.PARSE_ERROR)
            }
            return Args(commandLine)
        }

    }

}
