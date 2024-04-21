
pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.aliyun.com/repository/gradle-plugin")
        }
        maven {
            url = uri("'https://plugins.gradle.org/m2")
        }
        gradlePluginPortal()
    }
    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
        id("net.mamoe.mirai-console") version "2.16.0"
    }
}
rootProject.name = "OpenLightBit"

