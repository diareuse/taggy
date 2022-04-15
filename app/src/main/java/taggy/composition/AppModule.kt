package taggy.composition

import taggy.RemoteAdapter
import taggy.TagAdapter
import taggy.TagFacade

interface AppModule {

    fun getRemoteAdapter(): RemoteAdapter
    fun getTagAdapter(): TagAdapter
    fun getTagFacade(): TagFacade

}