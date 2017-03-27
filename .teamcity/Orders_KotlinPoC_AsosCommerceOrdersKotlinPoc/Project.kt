package Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.Project
import jetbrains.buildServer.configs.kotlin.v10.ProjectFeature
import jetbrains.buildServer.configs.kotlin.v10.ProjectFeature.*

object Project : Project({
    uuid = "4e9f2976-6440-44f5-bb42-6d70b988856f"
    extId = "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc"
    parentId = "Orders_KotlinPoC"
    name = "Asos.Commerce.Orders.KotlinPoc"

    params {
        param("Branch.Name", "RC")
    }

    features {
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
                    "sourceBuildTypeId": "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage",
                    "key": "BuildTestStatus"
                  }
                ]
            """.trimIndent())
            param("seriesTitle", "Serie")
            param("title", "New chart title")
        }
        feature {
            id = "PROJECT_EXT_111"
            type = "project-graphs"
            param("defaultFilters", "")
            param("hideFilters", "")
            param("series", """
                [
                  {
                    "type": "valueType",
                    "title": "Build Duration (all stages)",
                    "sourceBuildTypeId": "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage",
                    "key": "BuildDuration"
                  },
                  {
                    "type": "valueType",
                    "title": "Build Duration (excluding Checkout Time)",
                    "sourceBuildTypeId": "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage",
                    "key": "BuildDurationNetTime"
                  }
                ]
            """.trimIndent())
            param("seriesTitle", "Serie")
            param("title", "avg build tiome")
        }
        feature {
            id = "PROJECT_EXT_112"
            type = "project-graphs"
            param("defaultFilters", "")
            param("hideFilters", "")
            param("series", """
                [
                  {
                    "type": "valueType",
                    "title": "Number of Tests",
                    "sourceBuildTypeId": "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage",
                    "key": "TotalTestCount"
                  },
                  {
                    "type": "valueType",
                    "title": "Number of Passed Tests",
                    "sourceBuildTypeId": "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage",
                    "key": "PassedTestCount"
                  },
                  {
                    "type": "valueType",
                    "title": "Number of Ignored Tests",
                    "sourceBuildTypeId": "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage",
                    "key": "IgnoredTestCount"
                  }
                ]
            """.trimIndent())
            param("seriesTitle", "Serie")
            param("title", "New chart title")
        }
        feature {
            id = "PROJECT_EXT_113"
            type = "project-graphs.order"
            param("order", "PROJECT_EXT_103,PROJECT_EXT_111,PROJECT_EXT_112")
        }
        feature {
            id = "PROJECT_EXT_115"
            type = "versionedSettings"
            param("buildSettings", "PREFER_VCS")
            param("enabled", "true")
            param("format", "kotlin")
            param("rootId", "Orders_KotlinPoC_GitAsosCommerceKotlinPoC")
            param("showChanges", "true")
        }
    }
})
