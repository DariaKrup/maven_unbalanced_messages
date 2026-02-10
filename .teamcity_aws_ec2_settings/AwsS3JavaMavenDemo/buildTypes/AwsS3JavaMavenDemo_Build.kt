package AwsS3JavaMavenDemo.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.RunInDockerBuildFeature
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildFeatures.runInDocker
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object AwsS3JavaMavenDemo_Build : BuildType({
    name = "Build"

    artifactRules = """
        **/* => sources.zip
        *.txt
    """.trimIndent()

    vcs {
        root(AwsS3JavaMavenDemo.vcsRoots.AwsS3JavaMavenDemo_HttpsGithubComDariaKrupJavaMavenDemoRefsHeadsMaster)
    }

    steps {
        maven {
            id = "Maven2"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
        script {
            id = "simpleRunner"
            scriptContent = "fallocate -l 500M gentoo_root.txt"
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
        runInDocker {
            dockerImage = "ubuntu:latest"
            dockerImagePlatform = RunInDockerBuildFeature.ImagePlatform.Linux
        }
    }
})
