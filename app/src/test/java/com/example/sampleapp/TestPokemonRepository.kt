package com.example.sampleapp

import com.example.sampleapp.network.model.PokemonResponse
import com.example.sampleapp.repository.PokemonRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestPokemonRepository : PokemonRepository {
    private val topicsFlow: MutableSharedFlow<PokemonResponse> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun fetchPokemonList(): Flow<PokemonResponse> = topicsFlow

    /**
     * A test-only API to allow controlling the pokemon response from tests.
     */
    fun sendPokemon(pokemonResponse: PokemonResponse) {
        topicsFlow.tryEmit(pokemonResponse)
    }
}
