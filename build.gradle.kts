plugins {
    kotlin("jvm") version "2.0.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "app.revanced"

val githubUsername: String = project.findProperty("gpr.user") as? String ?: System.getenv("GITHUB_ACTOR")
val githubPassword: String = project.findProperty("gpr.key") as? String ?: System.getenv("GITHUB_TOKEN")

repositories {
    google()
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots") }
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://plugins.gradle.org/m2/") }
    maven {
        url = uri("https://maven.pkg.github.com/revanced/smali")
        credentials {
            username = githubUsername
            password = githubPassword
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.0.21")
    implementation("app.revanced:smali:2.5.3-a3836654")

    implementation("io.github.inotia00:revanced-patcher:8.0.2-arsclib-SNAPSHOT")
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.github.revanced:jadb:2531a28109") // updated fork
    implementation("com.android.tools.build:apksig:8.13.0")
    implementation("org.bouncycastle:bcpkix-jdk18on:1.82")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.5")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.0.21")
}

kotlin {
    jvmToolchain(17)
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("PASSED", "SKIPPED", "FAILED")
        }
    }
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        manifest {
            attributes("Main-Class" to "app.revanced.cli.main.MainKt")
        }
        minimize {
            exclude(dependency("org.jetbrains.kotlin:.*"))
            exclude(dependency("org.bouncycastle:.*"))
            exclude(dependency("app.revanced:.*"))
        }
    }
    // Dummy task to fix the Gradle semantic-release plugin.
    // Remove this if you forked it to support building only.
    // Tracking issue: https://github.com/KengoTODA/gradle-semantic-release-plugin/issues/435
    register<DefaultTask>("publish") {
        group = "publish"
        description = "Dummy task"
        dependsOn(build)
    }
}
