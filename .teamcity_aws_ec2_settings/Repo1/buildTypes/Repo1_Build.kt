package Repo1.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Repo1_Build : BuildType({
    name = "Build"

    vcs {
        root(Repo1.vcsRoots.Repo1_HttpsGithubComPavelsuprJbTestRepo1refsHeadsMain)
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
