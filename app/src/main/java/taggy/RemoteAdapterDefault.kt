package taggy

import taggy.provider.git.Remote

class RemoteAdapterDefault : RemoteAdapter {

    override fun adapt(name: String): Remote {
        return Remote(name)
    }

}