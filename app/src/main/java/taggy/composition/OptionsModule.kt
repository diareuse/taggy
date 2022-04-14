package taggy.composition

import org.apache.commons.cli.Option

interface OptionsModule {

    fun getPostfix(): Option
    fun getPostfixSeparator(): Option
    fun getVersion(): Option
    fun getPush(): Option
    fun getForce(): Option
    fun getEverywhere(): Option
    fun getPushTarget(): Option
    fun getPattern(): Option

}