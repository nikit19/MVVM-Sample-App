package com.example.sampleapp.repository

import com.example.sampleapp.network.model.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
     fun fetchPokemonList(
    ): Flow<PokemonResponse>
}