package com.example.premireapplication

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TmdbAPI {

    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String): TmdbMovieResult

    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String): TmdbSerieResult

    @GET("trending/person/week")
    suspend fun lastpeople(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): TmdbPersonResult

    @GET("movie/{id}")
    suspend fun detailmovie(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("append_to_response") append_to_response: String = "credits"
    ): TmdbMovieDetails

    @GET("tv/{id}")
    suspend fun detailserie(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("append_to_response") append_to_response: String = "credits"
    ): SerieDetails

    @GET("person/{id}")
    suspend fun detailperson(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("append_to_response") append_to_response: String = "credits"
    ): PersonDetails


    @GET("search/movie")
    suspend fun searchmovie(
        @Query("query") query: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): TmdbMovieResult

    @GET("search/tv")
    suspend fun searchserie(
        @Query("query") query: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): TmdbSerieResult

    @GET("search/person")
    suspend fun searchpeople(
        @Query("query") query: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): TmdbPersonResult

}






