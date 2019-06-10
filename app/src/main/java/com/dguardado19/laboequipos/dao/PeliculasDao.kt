package com.dguardado19.laboequipos.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.dguardado19.laboequipos.entities.Pelicula

@Dao
interface PeliculasDao {
    @Query("SELECT * FROM peliculas_table")
    suspend fun deletePeliculas ()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPelicula(peliculas: Pelicula)

    @Query("DELETE FROM peliculas_table")
    fun getAllPeliculas(): LiveData<List<Pelicula>>



}