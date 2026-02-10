package JavaMavenDemo.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.freeDiskSpace
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object JavaMavenDemo_Build : BuildType({
    name = "Build"

    vcs {
        root(JavaMavenDemo.vcsRoots.JavaMavenDemo_HttpsGithubComDariaKrupJavaMavenDemoGitRefsHeadsMaster)
    }

    steps {
        maven {
            id = "Maven2"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
        script {
            id = "simpleRunner"
            scriptContent = "sleep 60"
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
        freeDiskSpace {
            requiredSpace = "5gb"
            failBuild = true
        }
    }
})
