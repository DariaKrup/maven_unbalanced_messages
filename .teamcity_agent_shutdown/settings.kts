import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudImage
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudProfile
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2025.11"

project {

    buildType(BuildMavenSleep)

    features {
        awsConnection {
            id = "AWS_SubprojectWithDSL_AmazonWebServicesAwsLocal"
            name = "Amazon Web Services (AWS) (local)"
            regionName = "eu-west-1"
            credentialsType = static {
                accessKeyId = "AKIA5JH2VERVI62P5XDY"
                secretAccessKey = "credentialsJSON:5956c87f-9f8f-4ec4-8c89-2874bed09e35"
            }
            allowInSubProjects = true
            allowInBuilds = true
            stsEndpoint = "https://sts.eu-west-1.amazonaws.com"
        }
        amazonEC2CloudImage {
            id = "PROJECT_EXT_758"
            profileId = "amazon-61"
            imagePriority = 2
            name = "Ubuntu (AMI)"
            vpcSubnetId = "subnet-043178c302cabfe37,subnet-0c4f70b91d8800740"
            keyPairName = "daria.krupkina"
            instanceType = "t2.medium"
            securityGroups = listOf("sg-072d8bfa0626ea2a6")
            maxInstancesCount = 3
            source = Source("ami-07908fe7a17542f6b")
        }
        amazonEC2CloudProfile {
            id = "amazon-61"
            name = "EC2 (Amazon)"
            serverURL = "http://10.128.93.57:8281"
            terminateIdleMinutes = 30
            region = AmazonEC2CloudProfile.Regions.EU_WEST_DUBLIN
            awsConnectionId = "AWS_SubprojectWithDSL_AmazonWebServicesAwsLocal"
        }
    }
}

object BuildMavenSleep : BuildType({
    name = "Build: Maven + sleep"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            id = "Maven2"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
        script {
            id = "simpleRunner"
            scriptContent = "sleep 120"
        }
    }
})
