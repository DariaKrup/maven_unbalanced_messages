package Repo1

import Repo1.buildTypes.*
import Repo1.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project

object Project : Project({
    id("Repo1")
    name = "Repo1"

    vcsRoot(Repo1_HttpsGithubComPavelsuprJbTestRepo1refsHeadsMain1)
    vcsRoot(Repo1_HttpsGithubComPavelsuprJbTestRepo1refsHeadsMain)

    buildType(Repo1_Build1)
    buildType(Repo1_Build)
})
