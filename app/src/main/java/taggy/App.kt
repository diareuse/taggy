@file:JvmName("App")

package taggy

import taggy.composition.Compositor

fun main(args: Array<String>) {
    Compositor.getTaggy(args).createTag()
}