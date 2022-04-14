package taggy.composition

import org.apache.commons.cli.Option

class OptionsModuleDefault : OptionsModule {

    override fun getPostfix(): Option = Option.builder("a")
        .longOpt("postfix")
        .required(false)
        .hasArg()
        .desc("Appends postfix (after) to the latest fetched or provided version. If not provided, the program will try to reuse existing postfix from the last tag. Whenever the last tag contains number it's incremented if and only if the provided postfix matches with the tag's postfix. Even if this last tag has no postfixes it will be omitted. Increment is always preferred on postfix (1.0.0-dev1 -> 1.0.0-dev2), however if no postfix is found or provided the last number segment from the tag is incremented. (1.0.0 -> 1.0.1)")
        .build()

    override fun getPostfixSeparator(): Option = Option.builder()
        .longOpt("postfix-separator")
        .required(false)
        .hasArg()
        .desc("Separator which will be used for parsing appended postfix and appending the postfix when constructing tag. Beware that if you want to change separator, you need to provide the entire ensemble. (ie. postfix, postfix-separator and version)")
        .build()

    override fun getVersion(): Option = Option.builder("v")
        .longOpt("version")
        .required(false)
        .hasArg()
        .desc("Program will use this version code without any increments in order to construct new tag. Note that if postfix was provided it will use the postfix as well in the tag construction process.")
        .build()

    override fun getPush(): Option = Option.builder("p")
        .longOpt("push")
        .required(false)
        .desc("Informs the program that you want to also push the tag you created. This is by default to all remotes. You can limit remotes by using -t or --push-targets.")
        .build()

    override fun getForce(): Option = Option.builder("f")
        .longOpt("force")
        .required(false)
        .desc("Forces all tags on all remotes (or push targets) to be deleted and created again. Tags will be deleted locally and on the provided or all remotes. Please note that not clearing tags on all remotes might result in pull conflicts.")
        .build()

    override fun getEverywhere(): Option = Option.builder()
        .longOpt("everywhere")
        .required(false)
        .desc("[DEPRECATED] Instructs the program to look for tags in every branch. This might be useful when features are separated by branches and not bound to specific version. If disabled the program will look only into merged tags (ie. current branch).")
        .build()

    override fun getPushTarget(): Option = Option.builder("t")
        .longOpt("push-targets")
        .numberOfArgs(Option.UNLIMITED_VALUES)
        .hasArgs()
        .required(false)
        .desc("Specifies which targets are used in order to push the newly created tags. Targets are separated with \",\" with no spaces. When used -p and -t in conjunction, the targets must not be empty.")
        .build()

    override fun getPattern(): Option = Option.builder()
        .longOpt("pattern")
        .hasArg()
        .optionalArg(false)
        .required(true)
        .desc("Specifies java regex pattern for the program to use when listing tags. It bypasses git's implementation since it uses non-robust wildcard implementation. This parameter is required. See documentation for examples, visit regex101.com to test your implementation.")
        .build()

}