import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.ant
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.kubernetesCloudImage
import jetbrains.buildServer.configs.kotlin.kubernetesCloudProfile
import jetbrains.buildServer.configs.kotlin.projectFeatures.kubernetesConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.kubernetesExecutor
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

version = "2025.11"

project {

    vcsRoot(K8sWithKubernetesSDK2_HttpsGithubComDariaKrupAntProjectRefsHeadsMaster)
    vcsRoot(K8sWithKubernetesSDK2_HttpsGithubComDariaKrupBookingApiPayconiqRefsHeadsMaster)
    vcsRoot(K8sWithKubernetesSDK2_HttpsBitbucketOrgTeamcityTestSampleMavenAppRefsHeadsMaster)

    buildType(K8sWithKubernetesSDK2_BuildMavenUnbalancedMessagesTests)
    buildType(K8sWithKubernetesSDK2_BuildMavenBookingAPITest)
    buildType(K8sWithKubernetesSDK2_BuildSampleMaven)
    buildType(K8sWithKubernetesSDK2_BuildAnt)

    features {
        kubernetesConnection {
            id = "PROJECT_EXT_687"
            name = "Kubernetes Connection (EKS)"
            apiServerUrl = "https://A51B42A65F7E54005C95A4D353916627.gr7.eu-west-1.eks.amazonaws.com"
            authStrategy = eks {
                accessId = "AKIA5JH2VERVI62P5XDY"
                secretKey = "credentialsJSON:5956c87f-9f8f-4ec4-8c89-2874bed09e35"
                clusterName = "tc-dkrupkina-eks-cluster"
            }
        }
        kubernetesCloudImage {
            id = "PROJECT_EXT_689"
            profileId = "kube-9"
            agentPoolId = "-2"
            agentNamePrefix = "container-k8s-agent-single"
            maxInstancesCount = 1
            podSpecification = runContainer {
                dockerImage = "jetbrains/teamcity-agent:latest"
                arguments = ""
            }
        }
        kubernetesCloudImage {
            id = "PROJECT_EXT_691"
            profileId = "kube-9"
            agentPoolId = "-2"
            agentNamePrefix = "pod-template-k8s-agent"
            maxInstancesCount = 2
            podSpecification = customTemplate {
                customPod = """
                    apiVersion: v1
                    kind: Pod
                    metadata:
                      labels:
                        app: teamcity-agent
                    spec:
                      restartPolicy: Never
                      containers:
                        - name: teamcity-agent
                          image: jetbrains/teamcity-agent
                          resources:
                            limits:
                              memory: "2Gi"
                      nodeSelector:
                            kubernetes.io/os: linux
                """.trimIndent()
            }
        }
        kubernetesCloudImage {
            id = "PROJECT_EXT_692"
            profileId = "kube-9"
            agentPoolId = "-2"
            agentNamePrefix = "windows-pod-agent"
            maxInstancesCount = 3
            podSpecification = customTemplate {
                customPod = """
                    apiVersion: v1
                    kind: Pod
                    metadata:
                      labels:
                        app: teamcity-agent
                    spec:
                      restartPolicy: Never
                      containers:
                        - name: teamcity-agent
                          image: jetbrains/teamcity-agent:latest-windowsservercore
                      nodeSelector:
                            kubernetes.io/os: windows
                """.trimIndent()
            }
        }
        kubernetesExecutor {
            id = "PROJECT_EXT_693"
            connectionId = "PROJECT_EXT_687"
            profileName = "K8s Executor"
            buildsLimit = "2"
            serverURL = "http://10.128.93.57:8281"
            templateName = "linux-arm64-agent"
        }
        kubernetesCloudImage {
            id = "PROJECT_EXT_694"
            profileId = "kube-9"
            agentPoolId = "-2"
            agentNamePrefix = "teamcity-agent-linux-deployment"
            podSpecification = deploymentTemplate {
                deploymentName = "teamcity-agent-linux-deployment"
            }
        }
        kubernetesCloudImage {
            id = "PROJECT_EXT_696"
            profileId = "kube-10"
            agentPoolId = "-2"
            agentNamePrefix = "teamcity-agent-linux-deployment-from-role"
            maxInstancesCount = 1
            podSpecification = deploymentTemplate {
                deploymentName = "teamcity-agent-linux-deployment"
            }
        }
        kubernetesCloudImage {
            id = "PROJECT_EXT_697"
            profileId = "kube-11"
            agentPoolId = "-2"
            agentNamePrefix = "agent-deployment"
            podSpecification = deploymentTemplate {
                deploymentName = "teamcity-agent-linux-deployment"
            }
        }
        kubernetesConnection {
            id = "PROJECT_EXT_698"
            name = "Kubernetes Connection"
            apiServerUrl = "https://A51B42A65F7E54005C95A4D353916627.gr7.eu-west-1.eks.amazonaws.com"
            caCertificate = "credentialsJSON:f814bbb3-f403-42b0-9ec9-64a5328f46e0"
            authStrategy = eks {
                accessId = "AKIA5JH2VERVI62P5XDY"
                secretKey = "credentialsJSON:5956c87f-9f8f-4ec4-8c89-2874bed09e35"
                clusterName = "tc-dkrupkina-eks-cluster"
            }
        }
        kubernetesConnection {
            id = "PROJECT_EXT_701"
            name = "Kubernetes Connection (new eks)"
            apiServerUrl = "https://A51B42A65F7E54005C95A4D353916627.gr7.eu-west-1.eks.amazonaws.com"
            authStrategy = eks {
                accessId = "AKIA5JH2VERVI62P5XDY"
                secretKey = "credentialsJSON:5956c87f-9f8f-4ec4-8c89-2874bed09e35"
                clusterName = "tc-dkrupkina-eks-cluster"
            }
        }
        kubernetesCloudProfile {
            id = "kube-10"
            name = "K8s Cloud Agents (role)"
            serverURL = "http://10.128.93.57:8281"
            terminateIdleMinutes = 30
            apiServerURL = "https://A51B42A65F7E54005C95A4D353916627.gr7.eu-west-1.eks.amazonaws.com"
            authStrategy = eks {
                accessId = "AKIA5JH2VERVI62P5XDY"
                secretKey = "credentialsJSON:5956c87f-9f8f-4ec4-8c89-2874bed09e35"
                assumeIAMRole = true
                iamRoleArn = "arn:aws:iam::913206223978:role/dkrupkinaEc2Role"
                clusterName = "tc-dkrupkina-eks-cluster"
            }
        }
        kubernetesCloudProfile {
            id = "kube-11"
            name = "Role cloud profile"
            serverURL = "http://10.128.93.57:8281"
            terminateAfterBuild = true
            terminateIdleMinutes = 0
            apiServerURL = "https://A51B42A65F7E54005C95A4D353916627.gr7.eu-west-1.eks.amazonaws.com"
            authStrategy = eks {
                accessId = "AKIA5JH2VERVI62P5XDY"
                secretKey = "credentialsJSON:5956c87f-9f8f-4ec4-8c89-2874bed09e35"
                assumeIAMRole = true
                iamRoleArn = "arn:aws:iam::913206223978:role/dkrupkinaEc2Role"
                clusterName = "tc-dkrupkina-eks-cluster"
            }
        }
        kubernetesCloudProfile {
            id = "kube-9"
            name = "K8s Cloud Agents (keys)"
            serverURL = "http://10.128.93.57:8281/"
            terminateIdleMinutes = 10
            apiServerURL = "https://A51B42A65F7E54005C95A4D353916627.gr7.eu-west-1.eks.amazonaws.com"
            authStrategy = eks {
                accessId = "AKIA5JH2VERVI62P5XDY"
                secretKey = "credentialsJSON:5956c87f-9f8f-4ec4-8c89-2874bed09e35"
                iamRoleArn = ""
                clusterName = "tc-dkrupkina-eks-cluster"
            }
        }
    }
}

object K8sWithKubernetesSDK2_BuildAnt : BuildType({
    id("BuildAnt")
    name = "Build: Ant"

    vcs {
        root(K8sWithKubernetesSDK2_HttpsGithubComDariaKrupAntProjectRefsHeadsMaster)
    }

    steps {
        ant {
            id = "Ant"
            mode = antFile {
            }
            targets = "build"
        }
    }
})

object K8sWithKubernetesSDK2_BuildMavenBookingAPITest : BuildType({
    id("BuildMavenBookingAPITest")
    name = "Build: Maven BookingAPI test"

    vcs {
        root(K8sWithKubernetesSDK2_HttpsGithubComDariaKrupBookingApiPayconiqRefsHeadsMaster)
    }

    steps {
        maven {
            id = "Maven2"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }
})

object K8sWithKubernetesSDK2_BuildMavenUnbalancedMessagesTests : BuildType({
    id("BuildMavenUnbalancedMessagesTests")
    name = "Build: Maven (unbalanced messages tests)"

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

    features {
        perfmon {
        }
    }

    requirements {
        doesNotContain("teamcity.agent.name", "agent")
    }
})

object K8sWithKubernetesSDK2_BuildSampleMaven : BuildType({
    id("BuildSampleMaven")
    name = "Build: Sample Maven"

    vcs {
        root(K8sWithKubernetesSDK2_HttpsBitbucketOrgTeamcityTestSampleMavenAppRefsHeadsMaster)
    }

    steps {
        maven {
            id = "Maven2"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = bundled_3_9()
            jdkHome = "%env.JDK_11_0%"
        }
    }
})

object K8sWithKubernetesSDK2_HttpsBitbucketOrgTeamcityTestSampleMavenAppRefsHeadsMaster : GitVcsRoot({
    id("HttpsBitbucketOrgTeamcityTestSampleMavenAppRefsHeadsMaster")
    name = "https://bitbucket.org/teamcity-test/sample-maven-app#refs/heads/master"
    url = "https://bitbucket.org/teamcity-test/sample-maven-app"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = token {
        userName = "DariaKrupkina"
        tokenId = "tc_token_id:CID_760d9488da48e7ae8b5d9963601d845d:1:d29f05f0-ec19-4e5f-89b2-48e7f1e95830"
    }
    param("tokenType", "refreshable")
})

object K8sWithKubernetesSDK2_HttpsGithubComDariaKrupAntProjectRefsHeadsMaster : GitVcsRoot({
    id("HttpsGithubComDariaKrupAntProjectRefsHeadsMaster")
    name = "https://github.com/DariaKrup/AntProject#refs/heads/master"
    url = "https://github.com/DariaKrup/AntProject"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "DariaKrup"
        password = "credentialsJSON:a9f10039-e193-4665-afab-91b5b38ce930"
    }
})

object K8sWithKubernetesSDK2_HttpsGithubComDariaKrupBookingApiPayconiqRefsHeadsMaster : GitVcsRoot({
    id("HttpsGithubComDariaKrupBookingApiPayconiqRefsHeadsMaster")
    name = "https://github.com/DariaKrup/BookingApiPayconiq#refs/heads/master"
    url = "https://github.com/DariaKrup/BookingApiPayconiq"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = token {
        userName = "oauth2"
        tokenId = "tc_token_id:CID_4b4df26346ed38498f51c0d6bee05baa:1:8566f292-8528-4ac2-a483-0da5d4f66b79"
    }
    param("tokenType", "refreshable")
})
