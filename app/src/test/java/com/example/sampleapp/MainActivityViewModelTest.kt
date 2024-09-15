package com.example.sampleapp

import com.example.sampleapp.network.model.PokemonResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityViewModelTest {
    private lateinit var viewModel: MainActivityViewModel
    private val pokemonRepository = TestPokemonRepository()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = MainActivityViewModel(pokemonRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shouldEmitSuccessStateWhenRepositoryEmitsPokemonResponse () = runTest {
        // Given
        val expectedResponse = PokemonResponse(
            count = 1,
            next = "nextPage",
            previous = "previous",
            results = emptyList()
        )
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        assertEquals(
            MainActivityUiState.Loading,
            viewModel.uiState.value,
        )

        // When
        pokemonRepository.sendPokemon(expectedResponse)

        // Then
        assertEquals(
            MainActivityUiState.Success(emptyList()),
            viewModel.uiState.value,
        )
        collectJob.cancel()
    }
}