package com.dguardado19.laboequipos.retrofit

import com.dguardado19.laboequipos.entities.Movie
import com.dguardado19.laboequipos.entities.RetroCoincidencias
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val PELICULAS_DATABASE = "http://www.omdbapi.com/"


interface CoincidenciaRetrofit{


    @GET("/")
    fun obtenerPeliculas(@Query("s")clue : String, @Query("apikey") apikey:String) : Deferred<Response<RetroCoincidencias>>

    @GET("/")
    fun detallesMovies(@Query("i") id:String, @Query("apikey") apikey: String) : Deferred<Response<Movie>>

    companion object{
        fun getCoincidencias(): CoincidenciaRetrofit{
            return Retrofit.Builder().baseUrl(PELICULAS_DATABASE).addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory()).build().create(CoincidenciaRetrofit::class.java)
        }

        fun getFullDetails() : CoincidenciaRetrofit{
            return Retrofit.Builder().baseUrl(PELICULAS_DATABASE).addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory()).build().create(CoincidenciaRetrofit::class.java)
        }
    }

}