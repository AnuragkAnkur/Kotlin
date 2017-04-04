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
        param("ReleaseNotes.AdditionalParams", "")
        param("ReleaseNotes.TargetDiffEnvironment", "Production")
        password("ReleaseNotes.TeamCity.Password", "youni11#")
        param("ReleaseNotes.TeamCity.Uri", "http://localhost:8111")
        param("ReleaseNotes.TeamCity.UserId", "anuraga")
        param("Nuget.Version", "3.4.4")
        param("Octopus.Deployment.Timeout", "00:30:00")
        param("Octopus.Project.Name", "Deploy HelloWorld")
        param("Octopus.Uri", "http://localhost:90")
        param("Api.Key", "API-VE5E6V9F7VIUKAQX8A61O2E4W")

    }

    vcs {
        root(TestCoverageThroughTeamcity_KotlinPoc.vcsRoots.TestCoverageThroughTeamcity_KotlinPoc_KotlinGitVcs)
        checkoutMode = CheckoutMode.ON_SERVER
    }

    steps {
        /*step {
            name = "Download Generate-ReleaseNotes nuget package"
            type = "simpleRunner"
            param("script.content", """C:\Teamcity\tools\NuGet.CommandLine.%Nuget.Version%\tools\NuGet.exe Install Asos.PlatformEngineering.ReleaseNotesGenerator -Source https://proget.services.kingsway.asos.com/nuget/ASOS -ExcludeVersion""")
        }*/
        step {
            name = "Generate Release Notes"
            type = "jetbrains_powershell"
            param("jetbrains_powershell_bitness", "x64")
            param("jetbrains_powershell_scriptArguments", """
                -TeamCityURI %ReleaseNotes.TeamCity.Uri%
                -TeamCityUserId %ReleaseNotes.TeamCity.UserId%
                -TeamCityPassword %ReleaseNotes.TeamCity.Password%
                -DiffTargetEnvironment "%ReleaseNotes.TargetDiffEnvironment%"
                -OctopusURI %Octopus.Uri%
                -octopusAPIKey %Api.Key%
                -octopusProject "%Octopus.Project.Name%"
                -TeamCityProjectId "%teamcity.project.id%"
                -BuildNumber "%env.BUILD_NUMBER%"
                -BuildTypeId "%system.teamcity.buildType.id%"
                %ReleaseNotes.AdditionalParams%
            """.trimIndent())
            param("jetbrains_powershell_script_file", """Asos.PlatformEngineering.ReleaseNotesGenerator\Generate-ReleaseNotes.ps1""")
            param("jetbrains_powershell_script_mode", "FILE")
        }
        step {
            name = "Create release"
            type = "octopus.create.release"
            param("octopus_host", "%Octopus.Uri%")
            param("Octopus.Deployment.Timeout", "%Octopus.Deployment.Timeout%")
            param("octopus_project_name", "%Octopus.Project.Name%")
            param("octopus_version", "3.0+")
            param("octopus_waitfordeployments", "true")
            param("secure:octopus_apikey", "%Api.key%")
            param("octopus_additionalcommandlinearguments", "--deploymenttimeout=%Octopus.Deployment.Timeout% --cancelontimeout --releasenotesfile ReleaseNotes.md")
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