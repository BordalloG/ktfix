plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.21"
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.21")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0-rc1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:30.0-jre")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    api("org.apache.commons:commons-math3:3.6.1")
}
