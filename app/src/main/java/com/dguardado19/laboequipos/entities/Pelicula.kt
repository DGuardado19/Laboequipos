package com.dguardado19.laboequipos.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peliculas_table")
data class Pelicula(
    @PrimaryKey
    var ibID: String,
    var foto:String,
    var annio: String,
    var titulo:String,
    var genero:String
)