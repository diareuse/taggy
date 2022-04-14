package taggy.argument

interface ArgumentsFactory {

    fun create(arguments: Array<out String>): Arguments

}