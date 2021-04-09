package dev.chainmail.taggy.process

object ProcessStatus {

    const val PARSE_ERROR = 1
    const val DEPENDENCY_ERROR = 2
    const val PUSH_BRANCH_DOESNT_EXIST = 3
    const val NO_GIT = 4
    const val NO_TAGS = 5

}