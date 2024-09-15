package com.example.sampleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.model.PokemonUi
import com.example.sampleapp.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    pokemonRepository: PokemonRepository,
) : ViewModel() {

    val uiState: StateFlow<MainActivityUiState> = pokemonRepository.fetchPokemonList()
        .map { pokemonResponse ->
            MainActivityUiState.Success(pokemonResponse.results.map {
                PokemonUi(
                    name = it.name,
                    imageUrl = getImageUrl(it.url)
                )
            })
        }
        .catch { MainActivityUiState.Error }
        .stateIn(
            scope = viewModelScope,
            initialValue = MainActivityUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000),
        )
}

private fun getImageUrl(url: String): String {
    val index = url.split("/".toRegex()).dropLast(1).last()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
            "pokemon/other/official-artwork/$index.png"
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data object Error : MainActivityUiState
    data class Success(val pokemonUi: List<PokemonUi>) : MainActivityUiState
}

