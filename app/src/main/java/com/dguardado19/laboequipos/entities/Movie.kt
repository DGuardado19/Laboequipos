package com.dguardado19.laboequipos.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "peliculas_table")
data class Movie(
    var Title:String = "N/A",
    @PrimaryKey
    var imdbID:String = "N/A",
    var Type:String = "N/A",
    var Year:String = "N/A",
    var Poster: String = "N/A",
    var Plot:String = "N/A",
    var Country: String = "N/A",
    var imdbRating: String = "N/A",
    var Runtime: String = "N/A",
    var Director: String = "N/A",
    var Actors : String = "N/A",
    var Rated: String = "N/A",
    var Genre : String = "N/A",
    var Released: String = "N/A"

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        Plot = parcel.readString(),
        Country = parcel.readString(),
        imdbRating = parcel.readString(),
        Runtime = parcel.readString(),
        Director = parcel.readString(),
        Actors = parcel.readString(),
        Rated = parcel.readString(),
        Genre = parcel.readString(),
        Released = parcel.readString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Title)
        parcel.writeString(imdbID)
        parcel.writeString(Type)
        parcel.writeString(Year)
        parcel.writeString(Poster)
        parcel.writeString(Plot)
        parcel.writeString(Country)
        parcel.writeString(imdbRating)
        parcel.writeString(Runtime)
        parcel.writeString(Director)
        parcel.writeString(Actors)
        parcel.writeString(Rated)
        parcel.writeString(Genre)
        parcel.writeString(Released)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}