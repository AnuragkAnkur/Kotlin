package Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*

object TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage : BuildType({
    template = "CompilePackageAndRunUnitTests"
    uuid = "7d4d6c04-a359-4799-8c3c-dc9138ee760c"
    extId = "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage"
    name = "01 - Compile, run unit tests and package"

    allowExternalStatus = true

    params {
        param("Artifact.Paths", "")
        param("Branch.Root.Folder", "${'$'}/Asos/Commerce/OMScratchPad")
        param("Deployment.Package.Nuspec.Files", "%teamcity.build.checkoutDir%/%Source.Dir%/HelloWorldApp.nuspec")
        param("Solution.Name", "HelloWorldApp.sln")
        param("Version.Major", "1")
        param("Version.Minor", "0")
        param("Version.Revision", "0")
    }

    vcs {
        root("Commerce_VstsAsosCommerce")

        cleanCheckout = true
    }

    triggers {
        trigger {
            id = "vcsTrigger"
            type = "vcsTrigger"
        }
    }
    
    disableSettings("RUNNER_24", "RUNNER_28", "RUNNER_68")
})
