package com.example.proyectorifa.ui.theme


import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectorifa.model.RifaViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CrearRifaScreen(navController: NavController, viewModel: RifaViewModel) {
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha de la rifa (dd-MM-yyyy)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.guardarRifa(nombre, fecha)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}
