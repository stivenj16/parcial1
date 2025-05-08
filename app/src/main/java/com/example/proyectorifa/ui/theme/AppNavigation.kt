package com.example.proyectorifa.ui.theme


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectorifa.model.RifaViewModel

@Composable
fun AppNavigation(viewModel: RifaViewModel) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "lista_rifas") {
        composable("lista_rifas") {
            RifaListScreen(navController, viewModel)
        }
        composable("crear_rifa") {
            CrearRifaScreen(navController, viewModel)
        }
        composable("detalle_rifa/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (id != null) {
                DetalleRifaScreen(navController, viewModel, id)
            }
        }
    }
}