package com.example.sampleapp.network

import com.example.sampleapp.network.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): PokemonResponse

}
