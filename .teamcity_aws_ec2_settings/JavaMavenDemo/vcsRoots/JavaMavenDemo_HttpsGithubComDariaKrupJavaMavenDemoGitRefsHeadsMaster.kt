package JavaMavenDemo.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object JavaMavenDemo_HttpsGithubComDariaKrupJavaMavenDemoGitRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/DariaKrup/java-maven-demo.git#refs/heads/master"
    url = "https://github.com/DariaKrup/java-maven-demo.git"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "DariaKrup"
        password = "credentialsJSON:2b97a7f4-5df8-4f2d-b468-a7ad191c1f41"
    }
})
