plugins {
    kotlin("jvm") version "1.9.10"
    alias(libs.plugins.binary.compatibility.validator)
}

dependencies {
    implementation(libs.revanced.patcher)
    implementation(libs.kotlin.reflect)
    implementation(libs.jadb) // Updated fork
    implementation(libs.apksig)
    implementation(libs.bcpkix.jdk18on)
    implementation(libs.jackson.module.kotlin)

    testImplementation(libs.revanced.patcher)
    testImplementation(libs.kotlin.test)
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("PASSED", "SKIPPED", "FAILED")
        }
    }
}

kotlin { jvmToolchain(11) }

java {
    withSourcesJar()
}
