package com.dguardado19.laboequipos.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peliculas_table")
data class Pelicula(
    var Title:String,
    @PrimaryKey
    var imdbID:String,
    var Type:String,
    var Year:String,
    var Poster: String)
