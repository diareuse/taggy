package taggy.provider.git

data class Tag(
    val name: String
) {

    fun inverse() = copy(name = ":$name")

}