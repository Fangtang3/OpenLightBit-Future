plugins {
    id("java")
    kotlin("jvm") version "2.0.0"
}


group = "am9.openLightBit"
version = "9.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven ("https://maven.aliyun.com/repository/gradle-plugin")
    maven ("https://oss.sonatype.org/content/repositories/snapshots")
    maven ("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-test")
    implementation("org.apache.logging.log4j:log4j-core:3.0.0-beta2")
    compileOnly("net.mamoe:mirai-core:2.16.0") // mirai-core 的 API
    compileOnly("net.mamoe:mirai-console:2.16.0") // 后端
    testImplementation("net.mamoe:mirai-console-terminal:2.16.0") // 前端, 用于启动测试
    compileOnly("org.spigotmc:spigot-api:1.20.6-R0.1-SNAPSHOT")
    implementation("org.xerial:sqlite-jdbc:3.45.3.0")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")
    implementation("org.yaml:snakeyaml:2.2")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:2.0.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")
    implementation("org.codehaus.groovy:groovy:3.0.21")
}

tasks.test {
    useJUnitPlatform()
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    jvmToolchain(17)
}