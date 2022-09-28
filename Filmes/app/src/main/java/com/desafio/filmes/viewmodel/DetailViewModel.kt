package com.desafio.filmes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafio.filmes.model.ApiStatus
import com.desafio.filmes.model.Movie
import com.desafio.filmes.model.MovieDetails
import com.desafio.filmes.network.MovieApi
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    // Faz a requisição dos detalhes do filme passado como parãmetro. O status é atualizado para
    // permitir o tratamento de erros
    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _movieDetails.value = MovieApi.retrofitService.getMovieDetails(id)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }
}