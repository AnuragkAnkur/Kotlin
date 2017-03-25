package Orders_KotlinPoC.vcsRoots

import jetbrains.buildServer.configs.kotlin.v10.*

object Commerce_VstsAsosCommerceKotlinPoC : VcsRoot({
    uuid = "4c50693c-d3b5-41f4-81f0-f2b6a37f3d1f"
    extId = "Commerce_VstsAsosCommerceKotlinPoC"
    type = "tfs"
    name = "VSTS-ASOS-Commerce-KotlinPoC"
    pollInterval = 10
    param("secure:tfs-password", "zxx15498a00ece0a2ff9c32bb513695d3a7605f9a4f5c41a11d")
    param("tfs-root", "${'$'}/Asos/Commerce/OMScratchPad/KotlinPoC")
    param("tfs-url", "https://asos.visualstudio.com/DefaultCollection/")
    param("tfs-username", """##LIVE##\svc_TC_VSTS_Access""")
})
