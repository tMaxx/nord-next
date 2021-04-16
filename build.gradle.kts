plugins {
    id("org.jetbrains.intellij") version "0.7.2"
}

group = "pl.nmaxx.intellij"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2021.1"
}
//tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
//    changeNotes("""
//      Add change notes here.<br>
//      <em>most HTML tags may be used</em>""")
//}
