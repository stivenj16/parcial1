package com.example.proyectorifa.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
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
    var boletoGanador by remember { mutableStateOf(TextFieldValue("")) }
    var estaHabilitada by remember { mutableStateOf(true) }

    // Números inactivos (como los que aparecen en morado en la imagen)
    val numerosInactivos = listOf(1, 17, 31, 53, 88)
    var numerosSeleccionados by remember { mutableStateOf(listOf<Int>()) }

    LaunchedEffect(rifaId) {
        scope.launch {
            val rifa = viewModel.obtenerRifaPorId(rifaId)
            nombre = rifa?.nombre ?: "No encontrada"
            fecha = rifa?.fecha ?: "-"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Rifas $rifaId", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Grid de números
        LazyVerticalGrid(
            columns = GridCells.Fixed(10),
            modifier = Modifier
                .height(300.dp)
                .padding(8.dp)
        ) {
            items((1..99).toList()) { numero ->
                val numeroStr = numero.toString().padStart(2, '0')
                val isInactive = numero in numerosInactivos
                val isSelected = numero in numerosSeleccionados

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(2.dp)
                        .background(
                            color = when {
                                isInactive -> Color(0xFFE1BEE7) // Morado claro (inactivo)
                                isSelected -> Color(0xFFFFCDD2) // Rojo claro (seleccionado)
                                else -> Color.White
                            },
                            shape = MaterialTheme.shapes.small
                        )
                        .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                        .clickable(enabled = !isInactive) {
                            numerosSeleccionados = if (isSelected) {
                                numerosSeleccionados - numero
                            } else {
                                numerosSeleccionados + numero
                            }
                        }
                ) {
                    Text(text = numeroStr, style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Boleto ganador")
        TextField(
            value = boletoGanador,
            onValueChange = { boletoGanador = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Inhabilitar")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = !estaHabilitada,
                onCheckedChange = { estaHabilitada = !it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    // lógica de guardar
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5A5F))
            ) {
                Text("Guardar")
            }

            Button(
                onClick = {
                    // Llamar al métodoparaeliminar la rifa
                    scope.launch {
                        viewModel.eliminarRifa(rifaId)
                        navController.popBackStack()  // Regresar a la lista de rifas
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5A5F))
            ) {
                Text("Eliminar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}



