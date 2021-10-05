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

//  signPlugin {
//    certificateChain.set(System.getenv("JB_CERTIFICATE_CHAIN"))
//    privateKey.set(System.getenv("JB_PRIVATE_KEY"))
//    password.set(System.getenv("JB_PRIVATE_KEY_PASSWORD"))
//  }

  publishPlugin {
    System.getenv("JB_PLUGINS_CHANNEL")
      .let { if (it.isNullOrBlank()) "beta" else it }
      .let { channels.set(listOf(it)) }

    token.set(System.getenv("JB_PLUGINS_PUBLISH_TOKEN"))
  }
}
