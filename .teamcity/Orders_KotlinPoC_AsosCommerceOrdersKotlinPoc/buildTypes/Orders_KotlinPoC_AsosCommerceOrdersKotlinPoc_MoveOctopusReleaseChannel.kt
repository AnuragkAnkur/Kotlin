package Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.Trigger
import jetbrains.buildServer.configs.kotlin.v10.Trigger.*

object Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_MoveOctopusReleaseChannel : BuildType({
    template = "AmendOctopusReleaseChannels"
    uuid = "48fb9062-1036-40b2-a857-922bd58a2856"
    extId = "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_MoveOctopusReleaseChannel"
    name = "Move Octopus Release Channel"
    description = "Move the Octopus Release Channel to Day+1"

    params {
        param("Octopus.Channel.Name", "Day+1 & Baseline")
        param("Octopus.Project.Name", "%dep.Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseToProduction.Octopus.Project.Name%")
        param("Upstream.Build.Number", "%dep.Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseToProduction.build.number%")
    }

    triggers {
        trigger {
            id = "TRIGGER_1155"
            type = "buildDependencyTrigger"
            param("afterSuccessfulBuildOnly", "true")
            param("dependsOn", "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseToProduction")
        }
    }

    dependencies {
        dependency(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc.buildTypes.Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseToProduction) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }
        }
    }
})
