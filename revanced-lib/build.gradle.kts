plugins {
    kotlin("jvm") version "1.9.20"
}

dependencies {
    implementation(libs.revanced.patcher)
    implementation(libs.kotlin.reflect)
    implementation(libs.jadb) // Updated fork
    implementation(libs.apksig)
    implementation(libs.bcpkix.jdk18on)
    implementation(libs.jackson.module.kotlin)
}

tasks {
}

kotlin { jvmToolchain(11) }

java {
    withSourcesJar()
}
