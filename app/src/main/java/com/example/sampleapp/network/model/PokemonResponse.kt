package com.example.sampleapp.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    @SerialName("count") val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>,
)

@Serializable
data class Pokemon(
    val name: String,
    val url: String,
)