package TestCoverageThroughTeamCity_KotlinPoc.buildTypes

import TestCovereageThroughTeamcity_KotlinPoc.vcsRoots.TestCovereageThroughTeamcity_KotlinPoc_KotlinGitVcs
import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.script

object TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage : BuildType({
    uuid = "7d4d6c04-a359-4799-8c3c-dc9138ee760c"
    extId = "TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage"
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
        root(TestCovereageThroughTeamcity_KotlinPoc_KotlinGitVcs)

        cleanCheckout = true
    }

    steps{
        script{
            scriptContent = """Echo did something"""
        }
    }

    failureConditions{
        executionTimeoutMin = 10
        errorMessage = true
    }

    triggers {
        trigger {
            id = "vcsTrigger"
            type = "vcsTrigger"
        }
    }
    
    disableSettings("RUNNER_24", "RUNNER_28", "RUNNER_68")
})
