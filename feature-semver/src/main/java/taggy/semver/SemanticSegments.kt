package taggy.semver

data class SemanticSegments(
    val segments: List<Long>,
    val revision: Long?
)
