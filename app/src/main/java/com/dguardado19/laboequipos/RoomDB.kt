package com.dguardado19.laboequipos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dguardado19.laboequipos.dao.PeliculasDao
import com.dguardado19.laboequipos.entities.Movie

@Database(entities = [Movie::class],version = 4, exportSchema = false)
abstract class RoomDB : RoomDatabase(){
    abstract  fun PeliculaDao(): PeliculasDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDB?=null

        fun getDataBase(
            context: Context
        ):RoomDB{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "peliculas_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                instance
            }
        }
    }
}
