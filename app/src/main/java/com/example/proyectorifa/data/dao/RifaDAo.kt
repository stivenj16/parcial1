package com.example.proyectorifa.data.dao


import androidx.room.*
import com.example.proyectorifa.data.entidad.Rifa
import kotlinx.coroutines.flow.Flow

@Dao
interface RifaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarRifa(rifa: Rifa)

    @Delete
    suspend fun eliminarRifa(rifa: Rifa)

    @Query("SELECT * FROM rifa ORDER BY fecha DESC")
    fun obtenerTodasLasRifas(): Flow<List<Rifa>>

    @Query("SELECT * FROM rifa WHERE nombre LIKE '%' || :query || '%' OR fecha LIKE '%' || :query || '%' ORDER BY fecha DESC")
    fun buscarRifas(query: String): Flow<List<Rifa>>

    @Query("SELECT * FROM rifa WHERE id = :id")
    suspend fun obtenerRifaPorId(id: Int): Rifa?
}
