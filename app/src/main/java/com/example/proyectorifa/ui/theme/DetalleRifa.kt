package com.example.proyectorifa.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectorifa.model.RifaViewModel
import kotlinx.coroutines.launch

@Composable
fun DetalleRifaScreen(
    navController: NavController,
    viewModel: RifaViewModel,
    rifaId: Int
) {
    val scope = rememberCoroutineScope()
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    // Cargar los datos de la rifa por ID
    LaunchedEffect(rifaId) {
        scope.launch {
            val rifa = viewModel.obtenerRifaPorId(rifaId)
            nombre = rifa?.nombre ?: "No encontrada"
            fecha = rifa?.fecha ?: "-"
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Detalle de la Rifa", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Nombre: $nombre", style = MaterialTheme.typography.bodyLarge)
        Text("Fecha: $fecha", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}
