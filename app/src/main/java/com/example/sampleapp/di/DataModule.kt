package com.example.sampleapp.di

import com.example.sampleapp.repository.PokemonRepository
import com.example.sampleapp.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsNewsResourceRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl,
    ): PokemonRepository

}
