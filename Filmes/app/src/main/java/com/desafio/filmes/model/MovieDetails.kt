package com.desafio.filmes.model

data class MovieDetails(
    val title: String,
    val vote_average: Double,
    val genres: List<String>,
    val release_date: String,
    val production_countries: List<ProductionCountry>,
    val overview: String,
    val spoken_languages: List<SpokenLanguage>,
    val backdrop_url: String
)

data class ProductionCountry(
    val name: String
)

data class SpokenLanguage(
    val name: String
)
