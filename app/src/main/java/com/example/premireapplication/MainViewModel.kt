package com.example.premireapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val api_key = "7207dd94649f08047a55ef9572aa3129"
const val language = "fr"

class MainViewModel: ViewModel() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service: TmdbAPI = retrofit.create(TmdbAPI::class.java)

    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val series = MutableStateFlow<List<TmdbSerie>>(listOf())
    val people = MutableStateFlow<List<TmdbPerson>>(listOf())
    val filmDetail = MutableStateFlow<TmdbMovieDetails?>(null)
    val personDetail = MutableStateFlow<PersonDetails?>(null)
    val searchMovies = MutableStateFlow<List<TmdbMovie>>(listOf())

    init {
        getMovies()
        getSeries()
        getPeople()
    }

    fun getMovies() {
        viewModelScope.launch {
            val res = service.lastmovies(api_key)
            movies.value = res.results
        }
    }
    fun getSeries() {
        viewModelScope.launch {
            val res = service.lastseries(api_key)
            series.value = res.results
        }
    }
    fun getPeople() {
        viewModelScope.launch {
            val res = service.lastpeople(api_key, language)
            people.value = res.results
        }
    }

    fun getFilmDetails(id: Int) {
        viewModelScope.launch {
            if(id!=0){
                filmDetail.value = service.detailmovie(id, api_key, language)
            }
        }
    }

    fun getPersonDetails(id: Int) {
        viewModelScope.launch {
            if(id!=0){
                personDetail.value = service.detailperson(id, api_key, language)
            }
        }
    }
    fun getFilmSearch(query: String) {
        viewModelScope.launch {
            val res = service.searchmovie(query, api_key, language)
            searchMovies.value = res.results

        }
    }
}



