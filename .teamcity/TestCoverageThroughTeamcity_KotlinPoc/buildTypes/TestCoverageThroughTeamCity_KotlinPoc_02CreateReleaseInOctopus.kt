package TestCoverageThroughTeamcity_KotlinPoc.buildTypes

import TestCoverageThroughTeamCity_KotlinPoc.buildTypes.TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage
import jetbrains.buildServer.configs.kotlin.v10.BuildType
import jetbrains.buildServer.configs.kotlin.v10.CheckoutMode
import jetbrains.buildServer.configs.kotlin.v10.FailureAction

/**
 * Created by AnuragA on 02/04/2017.
 */
object TestCoverageThroughTeamCity_KotlinPoc_02CreateReleaseInOctopus : BuildType({
    uuid = "50e5be90-f989-416f-9638-548295840cc5"
    extId = "TestCoverageThroughTeamcity_KotlinPoc_CreateReleaseInOctopus"
    name = "Create Release In Octopus"
    buildNumberPattern = "%dep.TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage.build.number%"

    params{
        param("Nuget.Version", "3.4.4")
        param("Octopus.Deployment.Timeout", "00:30:00")
        param("Octopus.Project.Name", "Deploy HelloWorld")
        param("Octopus.Uri", "http://localhost:90/")
        param("Api.Key", """API-VE5E6V9F7VIUKAQX8A61O2E4W""")
        param("UpstreamBuildNumber", "%dep.TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage.build.number%")
    }

    vcs {
        root(TestCoverageThroughTeamcity_KotlinPoc.vcsRoots.TestCoverageThroughTeamcity_KotlinPoc_KotlinGitVcs)
        checkoutMode = CheckoutMode.ON_SERVER
    }

    steps {
        step {
            name = "Create Release note"
            type = "simpleRunner"
            param("script.content", """echo This is a release note > ReleaseNotes.md""")
        }
        step {
            name = "Create release"
            type = "octopus.create.release"
            param("octopus_host", "%Octopus.Uri%")
            param("Octopus.Deployment.Timeout", "%Octopus.Deployment.Timeout%")
            param("octopus_project_name", "%Octopus.Project.Name%")
            param("octopus_version", "3.0+")
            param("octopus_waitfordeployments", "true")
            param("secure:octopus_apikey", "API-2SKUQZHX0XVUMGPUXMRVVN2XHAM")
            param("octopus_additionalcommandlinearguments", "--deploymenttimeout=%Octopus.Deployment.Timeout% --cancelontimeout --version=%UpstreamBuildNumber% --releasenotesfile ReleaseNotes.md")
        }
    }

    triggers {
        trigger {
            type = "buildDependencyTrigger"
            param("afterSuccessfulBuildOnly", "true")
            param("dependsOn", "TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage")
        }
    }

    dependencies {
        dependency(TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage)
        {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }
        }
    }
})