package com.example.sampleapp.network

import com.example.sampleapp.network.model.PokemonResponse
import javax.inject.Inject

open class PokemonClient @Inject constructor(
    private val pokemonApi: PokemonApi,
) {

    suspend fun fetchPokemonList(): PokemonResponse =
        pokemonApi.fetchPokemonList(
            limit = PAGING_SIZE,
            offset =  PAGING_SIZE,
        )


    companion object {
        private const val PAGING_SIZE = 20
    }
}