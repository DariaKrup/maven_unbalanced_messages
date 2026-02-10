package Repo1.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object Repo1_HttpsGithubComPavelsuprJbTestRepo1refsHeadsMain : GitVcsRoot({
    name = "https://github.com/pavelsupr-jb-test/repo1#refs/heads/main"
    url = "https://github.com/pavelsupr-jb-test/repo1"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "pavelsupr-jb-test"
        password = "credentialsJSON:befec7c1-4da9-4713-be87-806a2ab031b2"
    }
})
