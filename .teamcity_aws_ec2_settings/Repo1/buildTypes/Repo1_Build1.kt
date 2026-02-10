package Repo1.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Repo1_Build1 : BuildType({
    name = "Build (1)"

    vcs {
        root(Repo1.vcsRoots.Repo1_HttpsGithubComPavelsuprJbTestRepo1refsHeadsMain1)
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})
