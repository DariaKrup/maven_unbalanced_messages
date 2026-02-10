package AwsS3JavaMavenDemo.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object AwsS3JavaMavenDemo_HttpsGithubComDariaKrupJavaMavenDemoRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/DariaKrup/java-maven-demo#refs/heads/master"
    url = "https://github.com/DariaKrup/java-maven-demo"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "DariaKrup"
        password = "credentialsJSON:3edebfe3-874b-4c87-a3d5-0b375a98fa84"
    }
})
