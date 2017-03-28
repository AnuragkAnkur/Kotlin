package TestCoverageThroughTeamCity_KotlinPoc.buildTypes

import TestCovereageThroughTeamcity_KotlinPoc.vcsRoots.TestCovereageThroughTeamcity_KotlinPoc_KotlinGitVcs
import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.script

object TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage : BuildType({
    uuid = "7d4d6c04-a359-4799-8c3c-dc9138ee760c"
    extId = "TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage"
    name = "01 - Compile, run unit tests and package"

    allowExternalStatus = true
    buildNumberPattern = "%Version.Number%"

    params {
        param("Artifact.Paths", "")
        param("Branch.Root.Folder", "${'$'}/Asos/Commerce/OMScratchPad")
        param("Deployment.Package.Nuspec.Files", "%teamcity.build.checkoutDir%/%Source.Dir%/HelloWorldApp.nuspec")
        param("Solution.Name", "HelloWorldApp.sln")
        param("Version.Major", "1")
        param("Version.Minor", "0")
        param("Version.Revision", "0")
        param("Version.Number", "%Version.Major%.%Version.Minor%.%Version.Build%.%Version.Revision%")
        param("Source.Dir", "Source")
        param("UnitTest.Path", """**\bin\**\*.UnitTests.dll""")
        param("Solution.Path", """%Source.Dir%\%Solution.Name%""")
        param("Build.Configuration", "Debug")
        param("MSBuild.AdditionalParameters", "/maxcpucount")
        param("MSBuild.Logging.Verbosity", "normal")
        param("Version.Build", "%build.counter%")
        param("ProGet.BaseUrl", "https://proget.services.kingsway.asos.com")
        param("UnitTest.Path", """**\bin\**\*.UnitTests.dll""")
    }

    vcs {
        root(TestCovereageThroughTeamcity_KotlinPoc_KotlinGitVcs)
        cleanCheckout = true
        checkoutMode = CheckoutMode.AUTO
    }

    steps{
        step {
            name = "Nuget Restore"
            type = "jb.nuget.installer"
            param("nuget.path", "%teamcity.tool.NuGet.CommandLine.3.5.0%")
            param("nuget.restore.commandline", "-verbosity detailed")
            param("nuget.sources", "%ProGet.BaseUrl%/nuget/ASOS")
            param("nuget.updatePackages.mode", "sln")
            param("sln.path", "%Solution.Path%")
            param("toolPathSelector", "%teamcity.tool.NuGet.CommandLine.3.5.0%")
        }
        step {
            name = "Build Solution and Package"
            type = "MSBuild"
            param("build-file-path", "%Solution.Path%")
            param("msbuild_version", "12.0")
            param("octopus_octopack_package_version", "%build.number%")
            param("run-platform", "x64")
            param("runnerArgs", "/p:Configuration=%Build.Configuration% /verbosity:%MSBuild.Logging.Verbosity% %MSBuild.AdditionalParameters%")
            param("toolsVersion", "12.0")
        }
        step {
            name = "Run unit tests"
            type = "NUnit"
            param("dotNetTestRunner.Type", "NUnit")
            param("nunit_enabled", "checked")
            param("nunit_environment", "v4.0")
            param("nunit_include", "%UnitTest.Path%")
            param("nunit_version", "NUnit-2.6.4")
            param("teamcity.tests.runRiskGroupTestsFirst", "recentlyFailed")
        }
    }

    features{
        feature {
            type = "JetBrains.AssemblyInfo"
            param("assembly-format", "%Version.Major%.%Version.Minor%.0.0")
            param("file-format", "%Version.Major%.%Version.Minor%.%Version.Build%.%Version.Revision%")
            param("info-format", "%Version.Number%")
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
