package taggy

import taggy.provider.git.Remote

interface RemoteAdapter {

    fun adapt(name: String): Remote

}