dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        maven { url = uri("https://jitpack.io") }
        google()
    }
}

buildCache {
    local {
        isEnabled = !System.getenv().containsKey("CI")
    }
}

include("revanced-cli", "revanced-lib")