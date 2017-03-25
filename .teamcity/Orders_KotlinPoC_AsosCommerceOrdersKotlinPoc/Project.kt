package Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc

import Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.Project
import jetbrains.buildServer.configs.kotlin.v10.ProjectFeature
import jetbrains.buildServer.configs.kotlin.v10.ProjectFeature.*

object Project : Project({
    uuid = "4e9f2976-6440-44f5-bb42-6d70b988856f"
    extId = "Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc"
    parentId = "Orders_KotlinPoC"
    name = "Asos.Commerce.Orders.KotlinPoc"

    buildType(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseTo26Test)
    buildType(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_MoveOctopusReleaseChannel)
    buildType(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseTo37Test)
    buildType(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage)
    buildType(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseTo76Test)
    buildType(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseTo02PerfTest)
    buildType(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseTo230Test)
    buildType(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseTo03PerfTest)
    buildType(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseToProduction)
    buildType(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_DeployReleaseToPreProduction)
    buildType(Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_CreateReleaseInOctopus)

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
    }
    buildTypesOrder = arrayListOf(
            Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc.buildTypes.Orders_KotlinPoC_AsosCommerceOrdersKotlinPoc_01CompileRunUnitTestsAndPackage
            ) as List<BuildType>?
})
