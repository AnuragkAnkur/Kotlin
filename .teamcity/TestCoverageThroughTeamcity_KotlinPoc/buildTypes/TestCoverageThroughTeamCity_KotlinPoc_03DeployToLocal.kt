package TestCoverageThroughTeamcity_KotlinPoc.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.BuildType
import jetbrains.buildServer.configs.kotlin.v10.CheckoutMode
import jetbrains.buildServer.configs.kotlin.v10.FailureAction

/**
 * Created by AnuragA on 06/04/2017.
 */
object TestCoverageThroughTeamCity_KotlinPoc_03DeployToLocal : BuildType({
    uuid = "af24b33e-5c67-42e6-9f8b-d0065d91ab78"
    extId = "TestCoverageThroughTeamcity_KotlinPoc_DeployToLocal"
    name = "Deploy release to local"

    params {
        param("Octopus.Deploy.Environment", "local")
        param("Octopus.Project.Name", "%dep.dep.TestCoverageThroughTeamCity_KotlinPoc_02CreateReleaseInOctopus.Octopus.Project.Name%")
        param("UpstreamBuildNumber", "%dep.TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage.build.number%")
        param("Octopus.Deployment.Timeout", "00:30:00")
        param("Octopus.Uri", "http://localhost:90/")
        param("Api.Key", """API-VE5E6V9F7VIUKAQX8A61O2E4W""")

    }

    vcs {
        root(TestCoverageThroughTeamcity_KotlinPoc.vcsRoots.TestCoverageThroughTeamcity_KotlinPoc_KotlinGitVcs)
        checkoutMode = CheckoutMode.ON_SERVER
    }

    steps{
        step {
            name = "Deploy Release"
            type = "octopus.deploy.release"
            param("octopus_host", "%Octopus.Uri%")
            param("Octopus.Deployment.Timeout", "%Octopus.Deployment.Timeout%")
            param("octopus_project_name", "%Octopus.Project.Name%")
            param("octopus_version", "3.0+")
            param("octopus_waitfordeployments", "true")
            param("secure:octopus_apikey", "API-2SKUQZHX0XVUMGPUXMRVVN2XHAM")
            param("octopus_additionalcommandlinearguments", "--deploymenttimeout=%Octopus.Deployment.Timeout% --cancelontimeout --releaseNumber=latest --deployto=local")
        }
    }

    dependencies {
        dependency(TestCoverageThroughTeamcity_KotlinPoc.buildTypes.TestCoverageThroughTeamCity_KotlinPoc_02CreateReleaseInOctopus) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }
        }
    }

    triggers {
        trigger {
            type = "buildDependencyTrigger"
            param("afterSuccessfulBuildOnly", "true")
            param("dependsOn", "TestCoverageThroughTeamCity_KotlinPoc_02CreateReleaseInOctopus")
        }
    }
})