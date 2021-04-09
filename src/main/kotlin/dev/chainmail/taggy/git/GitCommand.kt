package dev.chainmail.taggy.git

import dev.chainmail.taggy.command.Commander
import dev.chainmail.taggy.process.ProcessStatus
import dev.chainmail.taggy.startup.Args
import kotlin.system.exitProcess

class GitCommand private constructor(
    private val args: Args,
    private val commander: Commander
) {

    fun isGitRepository(): Boolean {
        return commander
            .runCatching { run("git", "remote") }
            .map { it.count() > 0 }
            .fold(
                onSuccess = { it },
                onFailure = { false }
            )
    }

    fun init(): Boolean {
        return commander
            .runCatching { run("git", "init") }
            .fold({ true }, { false })
    }

    fun createTag(tag: String) {
        commander
            .runCatching { run("git", "tag", "-a", tag, "-m", "via taggy") }
            .onSuccess { println("Created new tag $tag") }
            .onFailure {
                if (!args.force) {
                    println("Cannot create tag $tag. This tag might exists or no commits are available.")
                    exitProcess(ProcessStatus.DEPENDENCY_ERROR)
                }
                commander
                    .runCatching { run("git", "tag", "--delete", tag) }
                    .onSuccess { createTag(tag) }
                    .onFailure {
                        it.printStackTrace()
                        println("Cannot delete tag $tag.")
                        exitProcess(ProcessStatus.DEPENDENCY_ERROR)
                    }
            }
    }

    fun getTags(): Sequence<String> {
        println("Fetching all tags…")
        return commander
            .runCatching { run("git", "tag", "-l") }
            .getOrDefault(emptySequence())
    }

    fun push(tag: String) {
        if (!args.push) {
            println("Tag $tag processing is finalized.")
            return
        }

        val targets = args.pushTarget?.split(",") ?: getPushTargets()
        targets
            .filterNot { it.isBlank() }
            .forEach { push(it, tag) }
    }

    private fun push(target: String, tag: String) {
        if (args.force) {
            commander
                .runCatching { run("git", "push", target, ":$tag") }
                .onSuccess { println("Removed $tag from $it") }
        }
        commander
            .runCatching { run("git", "push", target, tag) }
            .onFailure { println("Cannot push $tag to $it, skipping…") }
            .onSuccess { println("Added $tag to $it") }
    }

    private fun getPushTargets(): List<String> {
        return commander
            .runCatching { run("git", "remote", "show") }
            .fold(
                onSuccess = { it.toList() },
                onFailure = { exitProcess(ProcessStatus.PUSH_BRANCH_DOESNT_EXIST) }
            )
    }

    class Builder {

        private lateinit var commander: Commander
        private lateinit var args: Args

        fun setArgs(args: Args) = apply {
            this.args = args
        }

        fun setCommander(commander: Commander) = apply {
            this.commander = commander
        }

        fun build(): GitCommand {
            return GitCommand(args = args, commander = commander)
        }

    }

}