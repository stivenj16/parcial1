package com.example.proyectorifa.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectorifa.data.RifaDatabase
import com.example.proyectorifa.data.entidad.Rifa
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RifaViewModel(application: Application) : AndroidViewModel(application) {
    private val rifaDao = RifaDatabase.getDatabase(application).rifaDao()

    private val _rifas = MutableStateFlow<List<Rifa>>(emptyList())
    val rifas = _rifas.asStateFlow()

    init {
        cargarRifas()
    }

    fun buscarRifas(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                rifaDao.obtenerTodasLasRifas().collect {
                    _rifas.value = it
                }
            } else {
                rifaDao.buscarRifas(query).collect {
                    _rifas.value = it
                }
            }
        }
    }

    fun guardarRifa(nombre: String, fecha: String) {
        viewModelScope.launch {
            val nueva = Rifa(nombre = nombre, fecha = fecha)
            rifaDao.insertarRifa(nueva)
            cargarRifas()
        }
    }

    private fun cargarRifas() {
        viewModelScope.launch {
            rifaDao.obtenerTodasLasRifas().collect {
                _rifas.value = it
            }
        }
    }
    suspend fun obtenerRifaPorId(id: Int): Rifa? {
        return rifaDao.obtenerRifaPorId(id)
    }

}
