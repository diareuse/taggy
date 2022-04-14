package taggy

import taggy.argument.Arguments

class TagFacadeArguments(
    private val arguments: Arguments,
    private val adapterTag: TagAdapter,
    private val adapterRemote: RemoteAdapter
) : TagFacade {

    override fun createTag() = adapterTag.adapt(
        version = arguments.version.let(::requireNotNull),
        postfix = arguments.postfix
    )

    override fun getRemotes() = arguments.pushTarget
        .map(adapterRemote::adapt)
        .also { require(it.isNotEmpty()) }

}