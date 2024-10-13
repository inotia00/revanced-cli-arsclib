package app.revanced.cli.signing

data class SigningOptions(
    val alias: String,
    val cn: String,
    val password: String,
    val keyStoreFilePath: String
)