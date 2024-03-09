package com.example.jokeappapiretrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("jokes/random")
    fun GetJokeFromAPI() : Call<DataModel>
}