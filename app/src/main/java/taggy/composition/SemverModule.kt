package taggy.composition

import taggy.semver.SemanticSegmentsAdapter
import taggy.semver.SemanticTagComparator
import taggy.semver.SemanticTagWrapperFactory

interface SemverModule {

    fun getSegmentsAdapter(): SemanticSegmentsAdapter
    fun getTagComparator(): SemanticTagComparator
    fun getTagWrapper(): SemanticTagWrapperFactory

}