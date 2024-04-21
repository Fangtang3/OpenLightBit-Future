plugins {
    kotlin("jvm") version "2.0.0-RC1"
}


group = "am9.openLightBit"
version = "1.0-SNAPSHOT"

repositories {
    //mavenCentral()
    maven("https://maven.aliyun.com/repository/gradle-plugin")
    maven ("https://oss.sonatype.org/content/repositories/snapshots")
    maven ("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    //testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.apache.logging.log4j:log4j-core:3.0.0-beta2")
    compileOnly("net.mamoe:mirai-core:2.16.0") // mirai-core 的 API
    compileOnly("net.mamoe:mirai-console:2.16.0") // 后端
    testImplementation("net.mamoe:mirai-console-terminal:2.16.0") // 前端, 用于启动测试
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}