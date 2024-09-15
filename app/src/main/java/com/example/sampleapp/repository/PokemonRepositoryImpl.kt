package com.example.sampleapp.repository

import com.example.sampleapp.network.PokemonClient
import com.example.sampleapp.network.model.PokemonResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class PokemonRepositoryImpl
@Inject constructor(
    private val pokemonClient: PokemonClient,
    private val ioDispatcher: CoroutineDispatcher,

    ) : PokemonRepository {
    override fun fetchPokemonList(
    ): Flow<PokemonResponse> = flow {
        emit(pokemonClient.fetchPokemonList())
    }.flowOn(ioDispatcher)
}