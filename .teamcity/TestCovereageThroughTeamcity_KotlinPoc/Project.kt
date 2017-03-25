package TestCovereageThroughTeamcity_KotlinPoc

import TestCovereageThroughTeamcity_KotlinPoc.vcsRoots.*
import TestCovereageThroughTeamcity_KotlinPoc.vcsRoots.TestCovereageThroughTeamcity_KotlinPoc_KotlinGitVcs
import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.Project
import jetbrains.buildServer.configs.kotlin.v10.ProjectFeature
import jetbrains.buildServer.configs.kotlin.v10.ProjectFeature.*
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.VersionedSettings.*
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.versionedSettings

object Project : Project({
    uuid = "86c98907-b825-403b-9b73-bc7ee5fa92f7"
    extId = "TestCovereageThroughTeamcity_KotlinPoc"
    parentId = "TestCovereageThroughTeamcity"
    name = "Kotlin Poc"
    description = "For Setting up a kotlin project"

    vcsRoot(TestCovereageThroughTeamcity_KotlinPoc_KotlinGitVcs)

    features {
        versionedSettings {
            id = "PROJECT_EXT_1"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = TestCovereageThroughTeamcity_KotlinPoc_KotlinGitVcs.extId
            showChanges = true
            settingsFormat = VersionedSettings.Format.KOTLIN
        }
        feature {
            id = "PROJECT_EXT_103"
            type = "project-graphs"
            param("defaultFilters", "")
            param("hideFilters", "")
            param("series", """
                [
                  {
                    "type": "valueType",
                    "title": "BuildTestStatus",
                    "sourceBuildTypeId": "TestCoverageThroughTeamCity_KotlinPoc_01CompileRunUnitTestsAndPackage",
                    "key": "BuildTestStatus"
                  }
                ]
            """.trimIndent())
            param("seriesTitle", "Serie")
            param("title", "New chart title")
        }
    }
})
