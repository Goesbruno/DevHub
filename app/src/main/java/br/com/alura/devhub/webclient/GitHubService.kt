package br.com.alura.devhub.webclient

import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("/users/{user}")

    suspend fun findProfileBy(@Path("user") user: String): GitHubProfileWeb

    @GET("/users/{user}/repos")
    suspend fun findRepositoriesBy(@Path("user") user: String): List<GitHubRepositoryWeb>

}