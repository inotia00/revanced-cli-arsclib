buildCache {
    local {
        isEnabled = !System.getenv().containsKey("CI")
    }
}

include("revanced-cli", "revanced-lib")