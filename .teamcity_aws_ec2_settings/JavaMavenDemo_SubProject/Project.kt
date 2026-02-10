package JavaMavenDemo_SubProject

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project

object Project : Project({
    id("JavaMavenDemo_SubProject")
    name = "SubProject"
})
