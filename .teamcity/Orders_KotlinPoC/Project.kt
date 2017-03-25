package Orders_KotlinPoC

import Orders_KotlinPoC.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.Project
import jetbrains.buildServer.configs.kotlin.v10.ProjectFeature
import jetbrains.buildServer.configs.kotlin.v10.ProjectFeature.*

object Project : Project({
    uuid = "89c975a2-026d-47f3-9b3f-193ab3073604"
    extId = "Orders_KotlinPoC"
    parentId = "Orders"
    name = "Kotlin PoC"
    description = "Order Management Kotlin PoC"

    vcsRoot(Commerce_VstsAsosCommerceKotlinPoC)

    features {
        feature {
            id = "PROJECT_EXT_114"
            type = "versionedSettings"
            param("buildSettings", "PREFER_VCS")
            param("enabled", "true")
            param("format", "kotlin")
            param("rootId", "Commerce_VstsAsosCommerceKotlinPoC")
            param("showChanges", "true")
        }
    }
})
