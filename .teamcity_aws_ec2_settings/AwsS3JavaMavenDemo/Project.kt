package AwsS3JavaMavenDemo

import AwsS3JavaMavenDemo.buildTypes.*
import AwsS3JavaMavenDemo.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project
import jetbrains.buildServer.configs.kotlin.projectFeatures.activeStorage
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.s3Storage

object Project : Project({
    id("AwsS3JavaMavenDemo")
    name = "[AWS S3] Java Maven Demo"

    vcsRoot(AwsS3JavaMavenDemo_HttpsGithubComDariaKrupJavaMavenDemoRefsHeadsMaster)

    buildType(AwsS3JavaMavenDemo_Build)

    features {
        s3Storage {
            id = "PROJECT_EXT_278"
            storageName = "AWS S3 Storage"
            bucketName = "tc-dkrupkina-acceleration"
            forceVirtualHostAddressing = true
            awsEnvironment = default {
                awsRegionName = "eu-west-1"
            }
            connectionId = "awsConnection_11"
        }
        activeStorage {
            id = "PROJECT_EXT_279"
            activeStorageID = "PROJECT_EXT_278"
        }
        awsConnection {
            id = "awsConnection_11"
            name = "Amazon Web Services (AWS)"
            regionName = "eu-west-1"
            credentialsType = static {
                accessKeyId = "AKIA5JH2VERVI62P5XDY"
                secretAccessKey = "credentialsJSON:5956c87f-9f8f-4ec4-8c89-2874bed09e35"
            }
            allowInSubProjects = true
            allowInBuilds = true
            stsEndpoint = "https://sts.eu-west-1.amazonaws.com"
        }
    }
})
