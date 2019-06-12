package com.dguardado19.laboequipos.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.dguardado19.laboequipos.dao.PeliculasDao
import com.dguardado19.laboequipos.entities.Movie
import com.dguardado19.laboequipos.entities.RetroCoincidencias
import com.dguardado19.laboequipos.retrofit.CoincidenciaRetrofit
import kotlinx.coroutines.Deferred
import retrofit2.Response

class peliculaRepository (private val Pelicula: PeliculasDao){
    fun retrieveRepoAsync(eje:String) : Deferred<Response<RetroCoincidencias>> {
        val apikey = "ffb96d82"
        return CoincidenciaRetrofit.getCoincidencias().obtenerPeliculas(eje,apikey)
    }

    fun getMovies(id: String) : Deferred<Response<Movie>>{
        val apikey = "ffb96d82"
        return CoincidenciaRetrofit.getFullDetails().detallesMovies(id,apikey)
    }

    @WorkerThread
    suspend fun nuke(){
        return Pelicula.deletePeliculas()
    }
    @WorkerThread
    suspend fun insertPeli(peli: Movie){
        Pelicula.insertPelicula(peli)
    }
    fun getAllPeliculas(): LiveData<List<Movie>> {
        return Pelicula.getAllPeliculas()
    }

}