package com.desafio.filmes.network

import com.desafio.filmes.model.Movie
import com.desafio.filmes.model.MovieDetails
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://desafio-mobile.nyc3.digitaloceanspaces.com/"

// Cria um objeto Moshi compatível com Kotlin para usar no Retrofit
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Cria um objeto Retrofit usando o Moshi para converter o JSON
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService {
    @GET("movies-v2")
    suspend fun getMovies(): List<Movie>

    @GET("movies-v2/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetails
}

// Api pública para realizar chamadas pelo Retrofit
object MovieApi {
    val retrofitService: MovieApiService by lazy { retrofit.create(MovieApiService::class.java) }
}
