package taggy.composition

import taggy.semver.*

class SemverModuleDefault : SemverModule {

    override fun getSegmentsAdapter(): SemanticSegmentsAdapter {
        return SemanticSegmentsAdapterDefault()
    }

    override fun getTagComparator(): SemanticTagComparator {
        return SemanticTagComparatorDefault(getSegmentsAdapter())
    }

    override fun getTagWrapper(): SemanticTagWrapperFactory {
        return SemanticTagWrapperFactoryDefault(getSegmentsAdapter())
    }

}