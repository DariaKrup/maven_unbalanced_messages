import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.projectFeatures.activeStorage
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.s3Storage
import jetbrains.buildServer.configs.kotlin.triggers.vcs

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

    buildType(BuildMaven)

    features {
        awsConnection {
            id = "MavenUnbalancedMessages_AmazonWebServicesAws"
            name = "Amazon Web Services (AWS)"
            regionName = "eu-south-1"
            credentialsType = static {
                accessKeyId = "AKIA5JH2VERVI62P5XDY"
                secretAccessKey = "credentialsJSON:eb053465-ece1-444c-af16-96f1ad63ccff"
            }
            allowInSubProjects = true
            allowInBuilds = true
            stsEndpoint = "https://sts.eu-south-1.amazonaws.com"
            param("awsSessionDuration", "")
        }
        s3Storage {
            id = "PROJECT_EXT_2"
            storageName = "S3"
            bucketName = "chubatovawest1"
            bucketPrefix = "maven-s3"
            forceVirtualHostAddressing = true
            connectionId = "MavenUnbalancedMessages_AmazonWebServicesAws"
        }
        activeStorage {
            id = "PROJECT_EXT_3"
            activeStorageID = "PROJECT_EXT_2"
        }
    }
}

object BuildMaven : BuildType({
    name = "Build: Maven"

    artifactRules = "+:%teamcity.build.checkoutDir%/target/*.jar"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            id = "Maven2"
            goals = "clean package"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
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
