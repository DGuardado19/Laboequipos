package com.dguardado19.laboequipos.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.dguardado19.laboequipos.entities.Movie

@Dao
interface PeliculasDao {
    @Query("DELETE FROM peliculas_table" )
    suspend fun deletePeliculas ()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPelicula(peliculas: Movie)

    @Query("SELECT * FROM peliculas_table")
    fun getAllPeliculas(): LiveData<List<Movie>>



}