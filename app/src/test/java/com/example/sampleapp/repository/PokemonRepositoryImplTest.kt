package com.example.sampleapp.repository

import com.example.sampleapp.network.PokemonClient
import com.example.sampleapp.network.model.PokemonResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class PokemonRepositoryImplTest {
    private lateinit var repository: PokemonRepositoryImpl

    private val pokemonClient: PokemonClient = mock()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        repository = PokemonRepositoryImpl(
            pokemonClient, testDispatcher
        )
    }


    @Test
    fun shouldCallPokemonClientWhenRepositoryIsInvoked() = runTest(testDispatcher) {
        // Given
        val expectedResponse = PokemonResponse(
            count = 1,
            next = "nextPage",
            previous = "previous",
            results = emptyList()
        )
        whenever(pokemonClient.fetchPokemonList()).thenReturn(
            expectedResponse
        )

        // When
        val result = repository.fetchPokemonList().first()

        // Then
        assertEquals(result, expectedResponse)
        verify(pokemonClient).fetchPokemonList()
    }
}