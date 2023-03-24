package br.com.alura.devhub.webclient

data class ProfileUiState(
    val login: String = "",
    val profilePic: String = "",
    val name: String = "",
    val bio: String = "",
    val repositories: List<GitHubRepository> = emptyList())