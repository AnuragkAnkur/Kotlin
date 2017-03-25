package Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.BuildFeature
import jetbrains.buildServer.configs.kotlin.v10.BuildFeature.*
import jetbrains.buildServer.configs.kotlin.v10.BuildStep
import jetbrains.buildServer.configs.kotlin.v10.BuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.Trigger
import jetbrains.buildServer.configs.kotlin.v10.Trigger.*

object Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseTo230Test : BuildType({
    template = "DeployReleaseToEnvironment"
    uuid = "9ffa33a2-4a46-4f5f-8033-09758d150340"
    extId = "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseTo230Test"
    name = "Deploy release to 230Test"

    params {
        param("Octopus.Deploy.Environment", "230Test")
        param("Octopus.Project.Name", "Asos.Commerce.Orders")
        param("Upstream.Build.Number", "%dep.Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_CreateReleaseInOctopus.build.number%")
    }

    steps {
        step {
            name = "Deploy release in Octopus Deploy (1)"
            id = "RUNNER_866"
            type = "octopus.deploy.release"
            enabled = false
            param("octopus_additionalcommandlinearguments", "--deploymenttimeout=%Octopus.Deployment.Timeout% --cancelontimeout")
            param("octopus_deployto", "%Octopus.Deploy.Environment%")
            param("octopus_host", "%Octopus.Uri%")
            param("octopus_project_name", "%Octopus.Project.Name%")
            param("octopus_releasenumber", "%Upstream.Build.Number%")
            param("octopus_version", "3.0+")
            param("octopus_waitfordeployments", "true")
            param("secure:octopus_apikey", "zxxe29f59f7fd27d0d7a158ab0297df86ad775d03cbe80d301b")
        }
        stepsOrder = arrayListOf("RUNNER_14", "RUNNER_866")
    }

    triggers {
        trigger {
            id = "TRIGGER_494"
            type = "buildDependencyTrigger"
            param("afterSuccessfulBuildOnly", "true")
            param("dependsOn", "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_CreateReleaseInOctopus")
        }
    }

    features {
        feature {
            id = "BUILD_EXT_19"
            type = "JetBrains.SharedResources"
            param("locks-param", "Exclusive_Lock230Test writeLock")
        }
    }

    dependencies {
        dependency(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc.buildTypes.Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_CreateReleaseInOctopus) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }
        }
    }
})
