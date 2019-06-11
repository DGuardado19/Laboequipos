package com.dguardado19.laboequipos.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dguardado19.laboequipos.RoomDB
import com.dguardado19.laboequipos.entities.Movie
import com.dguardado19.laboequipos.repository.peliculaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class peliculasViewModel(private val app: Application): AndroidViewModel(app){
    private val repository: peliculaRepository

    init {
        val PeliculasDao = RoomDB.getDataBase(app).PeliculaDao()
        repository= peliculaRepository(PeliculasDao)
    }

    fun getAllMovies(): LiveData<List<Movie>>{
        return repository.getAllPeliculas()
    }

    fun  insert(movie: Movie) = viewModelScope.launch (Dispatchers.IO){
        repository.insertPeli(movie)
    }
    private suspend fun nuke()= repository.nuke()


    fun retrievePelis(eje:String)= viewModelScope.launch {
        this@peliculasViewModel.nuke()

        val response =repository.retrieveRepoAsync(eje).await()
        if(response.isSuccessful)with(response.body()?.Search){
            this?.forEach {
                this@peliculasViewModel.insert(it)
                Log.d("respuesta",it.toString())
            }
        }else with(response){
            when(this.code()){
                404->{
                    Toast.makeText(app, "Nel", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}