package TestCoverageThroughTeamCity_KotlinPoc.buildTypes

import TestCoverageThroughTeamcity_KotlinPoc.vcsRoots.TestCoverageThroughTeamcity_KotlinPoc_KotlinGitVcs
import jetbrains.buildServer.configs.kotlin.v10.*

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
        param("ProGet.BaseUrl", "http://localhost:89")
        param("UnitTest.Path", """**\bin\**\*.UnitTests.dll""")
        param("NuGet.DeploymentPackage.PublishUrl", "%ProGet.BaseUrl%/nuget/Local")
        param("Deployment.Package.Nupkg.Files", """output\deployment\*.nupkg""")

    }

    vcs {
        root(TestCoverageThroughTeamcity_KotlinPoc_KotlinGitVcs)
        cleanCheckout = true
        checkoutMode = CheckoutMode.AUTO
    }

    steps{
        step {
            name = "Nuget Restore"
            type = "jb.nuget.installer"
            param("nuget.path", "%teamcity.tool.NuGet.CommandLine.3.5.0%")
            param("nuget.restore.commandline", "-verbosity detailed")
            param("nuget.sources", "%ProGet.BaseUrl%/nuget/Local")
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
        step {
            name = "Create Deployment Package (for Octopus to deploy)"
            type = "jb.nuget.pack"
            param("nuget.pack.output.clean", "true")
            param("nuget.pack.output.directory", """output\deployment""")
            param("nuget.pack.properties", "Configuration=%Build.Configuration%")
            param("nuget.pack.specFile", "%Deployment.Package.Nuspec.Files%")
            param("nuget.pack.version", "%build.number%")
            param("nuget.path", "%teamcity.tool.NuGet.CommandLine.DEFAULT%")
            param("nugetCustomPath", "%teamcity.tool.NuGet.CommandLine.DEFAULT%")
            param("nugetPathSelector", "%teamcity.tool.NuGet.CommandLine.DEFAULT%")
        }
        step {
            name = "Publish Deployment Packages to Proget"
            type = "jb.nuget.publish"
            param("nuget.path", "%teamcity.tool.NuGet.CommandLine.DEFAULT%")
            param("nuget.publish.files", "%Deployment.Package.Nupkg.Files%")
            param("nuget.publish.source", "%NuGet.DeploymentPackage.PublishUrl%")
            param("nugetCustomPath", "%teamcity.tool.NuGet.CommandLine.DEFAULT%")
            param("nugetPathSelector", "%teamcity.tool.NuGet.CommandLine.DEFAULT%")
            param("secure:nuget.api.key", "JrP9x14T83l8bUtA9rEjuQ==")
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
