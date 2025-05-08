package com.example.proyectorifa.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectorifa.data.dao.RifaDao
import com.example.proyectorifa.data.entidad.Rifa

@Database(entities = [Rifa::class], version = 1, exportSchema = false)
abstract class RifaDatabase : RoomDatabase() {

    abstract fun rifaDao(): RifaDao

    companion object {
        @Volatile
        private var INSTANCE: RifaDatabase? = null

        fun getDatabase(context: Context): RifaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RifaDatabase::class.java,
                    "rifa_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

