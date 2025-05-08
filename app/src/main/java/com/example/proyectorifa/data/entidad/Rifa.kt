package com.example.proyectorifa.data.entidad

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rifa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val fecha: String
)
