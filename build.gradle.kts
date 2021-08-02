fun prop(key: String) = project.findProperty(key).toString()

plugins {
  id("org.jetbrains.intellij") version "1.1.4"
}

group = prop("pluginGroup")
version = prop("pluginVersion")

repositories {
  mavenCentral()
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
  pluginName.set(prop("pluginName"))
  version.set(prop("platformVersion"))
  type.set(prop("platformType"))
  downloadSources.set(prop("platformDownloadSources").toBoolean())
  updateSinceUntilBuild.set(true)
}

tasks {
  patchPluginXml {
    version.set(prop("pluginVersion"))
    sinceBuild.set(prop("pluginSinceBuild"))
    untilBuild.set(prop("pluginUntilBuild"))
  }
}
