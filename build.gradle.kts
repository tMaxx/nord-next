fun prop(key: String) = project.findProperty(key).toString()
fun groovyClosure(param: (Any, Any) -> String?) = KotlinClosure2(param)

plugins {
  id("org.jetbrains.intellij") version "1.5.2"
  id("pl.allegro.tech.build.axion-release") version "1.13.6"
}

scmVersion {
  tag.initialVersion = groovyClosure { _, _ -> "0.1.4" }
  snapshotCreator = groovyClosure { _, _ -> "" }
  useHighestVersion = true
}

group = prop("pluginGroup")
version = scmVersion.version

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
    version.set(scmVersion.version)
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

    println("publishing version ${scmVersion.version} :: project set version is $version")

    token.set(System.getenv("JB_PLUGINS_PUBLISH_TOKEN"))
  }
}
