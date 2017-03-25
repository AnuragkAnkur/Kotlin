package Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.Trigger
import jetbrains.buildServer.configs.kotlin.v10.Trigger.*

object Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_CreateReleaseInOctopus : BuildType({
    template = "CreateReleaseInOctopus"
    uuid = "ef22f29b-35c5-4d1b-b03d-6af4e22c4ff4"
    extId = "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_CreateReleaseInOctopus"
    name = "02 - Create release in Octopus"

    params {
        param("Octopus.Deployment.Timeout", "00:30:00")
        param("Octopus.Project.Name", "Asos.Commerce.Orders")
        param("Upstream.Build.Number", "%dep.Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage.build.number%")
    }

    triggers {
        trigger {
            id = "TRIGGER_11"
            type = "buildDependencyTrigger"
            param("afterSuccessfulBuildOnly", "true")
            param("dependsOn", "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage")
        }
    }

    dependencies {
        dependency(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc.buildTypes.Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }
        }
    }
})
