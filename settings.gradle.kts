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
        isEnabled = "CI" !in System.getenv()
    }
}

include("revanced-cli", "revanced-lib")