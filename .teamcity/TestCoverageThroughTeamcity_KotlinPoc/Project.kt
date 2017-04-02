package TestCoverageThroughTeamcity_KotlinPoc

import TestCoverageThroughTeamCity_KotlinPoc.buildTypes.TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage
import TestCoverageThroughTeamcity_KotlinPoc.buildTypes.TestCoverageThroughTeamCity_KotlinPoc_02CreateReleaseInOctopus
import TestCoverageThroughTeamcity_KotlinPoc.vcsRoots.TestCoverageThroughTeamcity_KotlinPoc_KotlinGitVcs
import jetbrains.buildServer.configs.kotlin.v10.Project
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.versionedSettings

object Project : Project({
    uuid = "86c98907-b825-403b-9b73-bc7ee5fa92f7"
    extId = "TestCoverageThroughTeamcity_KotlinPoc"
    parentId = "TestCoverageThroughTeamcity"
    name = "Kotlin Poc"
    description = "For Setting up a kotlin project"

    vcsRoot(TestCoverageThroughTeamcity_KotlinPoc_KotlinGitVcs)

    features {
        versionedSettings {
            id = "PROJECT_EXT_1"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = TestCoverageThroughTeamcity_KotlinPoc_KotlinGitVcs.extId
            showChanges = true
            settingsFormat = VersionedSettings.Format.KOTLIN
        }
    }

    buildType(TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage)
    buildType(TestCoverageThroughTeamCity_KotlinPoc_02CreateReleaseInOctopus)
})
