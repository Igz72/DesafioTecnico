package com.desafio.filmes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafio.filmes.model.ApiStatus
import com.desafio.filmes.model.Movie
import com.desafio.filmes.network.MovieApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    init {
        // Atualiza a lista de filmes na criação da ViewModel
        getMovies()
    }

    // Faz a requisição da lista de filmes. O status é atualizado para permitir o tratamento de
    // erros
    private fun getMovies() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _movies.value = MovieApi.retrofitService.getMovies()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _movies.value = listOf()
                _status.value = ApiStatus.ERROR
            }
        }
    }
}