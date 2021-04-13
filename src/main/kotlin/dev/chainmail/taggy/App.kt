package dev.chainmail.taggy

import dev.chainmail.taggy.command.Commander
import dev.chainmail.taggy.git.GitCommand
import dev.chainmail.taggy.pattern.Tag
import dev.chainmail.taggy.process.ProcessStatus
import dev.chainmail.taggy.startup.Args
import kotlin.system.exitProcess

class App private constructor(
    private val args: Args
) {

    private val commander = Commander.Builder()
        .setRuntime(Runtime.getRuntime())
        .build()

    private val git = GitCommand.Builder()
        .setArgs(args)
        .setCommander(commander)
        .build()

    private fun start() {
        if (!git.isGitRepository() && !git.init()) {
            println("Cannot find or initialize git repository")
            exitProcess(ProcessStatus.NO_GIT)
        }

        val proposedVersion = args.version
        val tag = if (proposedVersion != null) {
            Tag(proposedVersion, args.postfix, args.postfixSeparator).let {
                if (it.postfix != null && !it.postfixHasNumbers()) it.inc(incrementName = false) else it
            }
        } else {
            git.getTags()
                .filter { args.pattern.matches(it) }
                .map { Tag.from(it, args.postfix, args.postfixSeparator) }
                .sortedByDescending { it.code }
                .firstOrNull()
                ?.inc() ?: exitProcess(ProcessStatus.NO_TAGS)
        }

        git.createTag(tag.toString())
        git.push(tag.toString())
    }

    class Builder {

        private lateinit var args: Args

        fun setArgs(builder: Args) = apply {
            this.args = builder
        }

        fun start() = App(
            args = this.args
        ).apply {
            start()
        }

    }

}