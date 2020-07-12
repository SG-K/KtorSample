package com.example.ktorsample.reposearch.models


import kotlinx.serialization.Serializable

@Serializable
data class Owner(
                 val gists_url: String = "",
                 val repos_url: String = "")


@Serializable
data class GitHubSearchResponse(
                                val total_count: Int = 0,
                                val incomplete_results: Boolean = false,
                                val items: List<ItemsItem>?)

@Serializable
data class ItemsItem(
                     val stargazers_count: Int = 0,
                     val name: String = "")


