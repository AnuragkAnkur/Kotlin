package TestCovereageThroughTeamcity_KotlinPoc.vcsRoots

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.vcs.GitVcsRoot

object TestCovereageThroughTeamcity_KotlinPoc_KotlinGitVcs : GitVcsRoot({
    uuid = "5b8589dd-eebe-40dc-906f-12d3fed366be"
    extId = "TestCovereageThroughTeamcity_KotlinPoc_KotlinGitVcs"
    name = "Kotlin_Git_VCS"
    url = "https://github.com/AnuragkAnkur/Kotlin.git"
    pushUrl = "https://github.com/AnuragkAnkur/Kotlin.git"
    authMethod = password {
        userName = "anuragankur1107@gmail.com"
        password = "zxx7bd42ff5ef5fd5b9775d03cbe80d301b"
    }
})
