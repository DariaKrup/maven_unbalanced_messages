import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudImage
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudProfile
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.kubernetesCloudImage
import jetbrains.buildServer.configs.kotlin.kubernetesCloudProfile
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

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

version = "2026.2"

project {

    vcsRoot(HttpsGithubComDariaKrupGradleCinemaplexRefsHeadsMain)

    buildType(EchoSleep)
    buildType(BuildMaven)

    features {
        kubernetesCloudImage {
            id = "PROJECT_EXT_32"
            profileId = "kube-4"
            agentPoolId = "-2"
            agentNamePrefix = "deployment-k8s"
            podSpecification = deploymentTemplate {
                deploymentName = "teamcity-agent-linux-deployment"
            }
        }
        amazonEC2CloudImage {
            id = "PROJECT_EXT_34"
            profileId = "amazon-8"
            agentPoolId = "-2"
            name = "Ubuntu EC2"
            vpcSubnetId = "subnet-043178c302cabfe37,subnet-0c4f70b91d8800740"
            keyPairName = "daria.krupkina"
            instanceType = "t2.large"
            securityGroups = listOf("sg-072d8bfa0626ea2a6")
            source = Source("ami-0effbc367ff0a08aa")
        }
        amazonEC2CloudProfile {
            id = "amazon-8"
            name = "AWS EC2"
            terminateIdleMinutes = 30
            region = AmazonEC2CloudProfile.Regions.EU_WEST_DUBLIN
            awsConnectionId = "AmazonWebServicesAws"
        }
        kubernetesCloudProfile {
            id = "kube-4"
            name = "K8s agents"
            terminateIdleMinutes = 7
            apiServerURL = "https://A51B42A65F7E54005C95A4D353916627.gr7.eu-west-1.eks.amazonaws.com"
            authStrategy = eks {
                accessId = "AKIA5JH2VERVI62P5XDY"
                secretKey = "credentialsJSON:6b3d01f1-df18-4025-a809-051cb1d73e62"
                clusterName = "tc-dkrupkina-eks-cluster"
            }
        }
    }

    pipeline(GradleCinemaplexPipeline)
}

object BuildMaven : BuildType({
    name = "Build: Maven"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            id = "Maven2"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }
})

object EchoSleep : BuildType({
    name = "Echo + sleep"

    steps {
        script {
            id = "simpleRunner"
            scriptContent = """
                echo "Going to sleep..."
                sleep 2m
                echo "Awake!"
            """.trimIndent()
        }
    }
})

object HttpsGithubComDariaKrupGradleCinemaplexRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/DariaKrup/gradle-cinemaplex#refs/heads/main"
    url = "https://github.com/DariaKrup/gradle-cinemaplex"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = token {
        userName = "oauth2"
        tokenId = "tc_token_id:CID_115af68de2d05e331e5f6568f57baa66:-1:b4eb72ec-88b9-4fc8-91f1-0f91cac368f0"
    }
    param("pipelines.connectionId", "PROJECT_EXT_33")
    param("tokenType", "refreshable")
})


object GradleCinemaplexPipeline : Pipeline({
    name = "Gradle Cinemaplex: pipeline"

    repositories {
        repository(HttpsGithubComDariaKrupGradleCinemaplexRefsHeadsMain)
    }

    triggers {
        vcs {
        }
    }

    job(GradleCinemaplexPipeline_Job1)
})

object GradleCinemaplexPipeline_Job1 : Job({
    id("Job1")
    name = "Job 1"

    steps {
        gradle {
            name = "Gradle tests"
        }
    }
})
