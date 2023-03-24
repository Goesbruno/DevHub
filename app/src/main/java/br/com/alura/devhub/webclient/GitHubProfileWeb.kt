package br.com.alura.devhub.webclient


import com.squareup.moshi.Json


data class GitHubProfileWeb(
    val name: String,
    val login: String,
    val bio: String,
    @field:Json(name = "avatar_url")
    val profilePic: String
) {
    fun toProfileUiState(): ProfileUiState {
        return ProfileUiState(
            login = login,
            name = name,
            bio = bio,
            profilePic = profilePic
        )
    }
}



