package TestCoverageThroughTeamcity_KotlinPoc.buildTypes

import TestCoverageThroughTeamCity_KotlinPoc.buildTypes.TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage
import jetbrains.buildServer.configs.kotlin.v10.BuildType

/**
 * Created by AnuragA on 02/04/2017.
 */
object TestCoverageThroughTeamCity_KotlinPoc_02CreateReleaseInOctopus : BuildType({
    uuid = "50e5be90-f989-416f-9638-548295840cc5"
    extId = "TestCoverageThroughTeamcity_KotlinPoc_CreateReleaseInOctopus"
    name = "Create Release In Octopus"

    vcs {
        root(TestCoverageThroughTeamcity_KotlinPoc.vcsRoots.TestCoverageThroughTeamcity_KotlinPoc_KotlinGitVcs)

    }

    steps {
        step {
            name = "Create release"
            type = "octopus.create.release"
            param("octopus_host", "http://localhost:90")
            param("octopus_project_name", "Deploy HelloWorld")
            param("octopus_version", "3.0+")
            param("octopus_waitfordeployments", "true")
            param("secure:octopus_apikey", "zxx17507688bb50ccb1ddb978fa8567e24777aef946ce4abc8041eca78dd7c34de0")
        }
    }

    triggers {
        vcs {
        }
    }

    dependencies {
        dependency(TestCoverageThroughTeamCity_KotlinPoc_02CreateReleaseInOctopus)
        {
            snapshot {  }
        }
    }
})