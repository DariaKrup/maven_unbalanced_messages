package _Self

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudImage
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudProfile
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection

object Project : Project({

    val ec2InstanceId = "i-0c609ac124457a9ac"
    features {
        awsConnection {
            id = "AwsEc2Profile_AmazonWebServicesAws1"
            name = "Amazon Web Services (AWS) (1)"
            regionName = "eu-west-1"
            credentialsType = static {
                accessKeyId = "AKIA5JH2VERVI62P5XDY"
                secretAccessKey = "credentialsJSON:5956c87f-9f8f-4ec4-8c89-2874bed09e35"
                useSessionCredentials = false
            }
            allowInSubProjects = true
            allowInBuilds = true
            stsEndpoint = "https://sts.eu-west-1.amazonaws.com"
        }
        amazonEC2CloudImage {
            id = "PROJECT_EXT_760"
            profileId = "amazon-58"
            agentPoolId = "-2"
            name = "Windows-$ec2InstanceId"
            vpcSubnetId = "subnet-0c23f411b0800b216"
            keyPairName = "daria.krupkina"
            instanceType = "t2.medium"
            securityGroups = listOf("sg-072d8bfa0626ea2a6")
            source = Source(ec2InstanceId)
        }
        amazonEC2CloudProfile {
            id = "amazon-58"
            name = "AWS"
            serverURL = "http://10.128.93.57:8281"
            terminateIdleMinutes = 30
            region = AmazonEC2CloudProfile.Regions.EU_WEST_DUBLIN
            awsConnectionId = "AwsEc2Profile_AmazonWebServicesAws1"
        }
    }

    subProject(SubProject.Project)
    subProject(AwsS3JavaMavenDemo.Project)
    subProject(Repo1.Project)
})
