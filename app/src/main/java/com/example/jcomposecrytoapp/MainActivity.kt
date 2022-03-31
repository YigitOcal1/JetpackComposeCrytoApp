package com.example.jcomposecrytoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jcomposecrytoapp.view.CryptoDetailScreen
import com.example.jcomposecrytoapp.view.CryptoListScreen
import com.example.jcomposecrytoapp.ui.theme.JComposeCrytoAppTheme
import androidx.compose.runtime.remember
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JComposeCrytoAppTheme {
                val navController= rememberNavController()
                NavHost(navController = navController, startDestination = "crypto_list_screen"){

                    composable("crypto_list_screen"){
                        CryptoListScreen(navController = navController)
                    }
                    composable("crypto_detail_screen/{cryptoId}/{cryptoPrice}",arguments = listOf(
                        navArgument(name="cryptoId",){
                            type=NavType.StringType
                        },
                        navArgument(name="cryptoPrice"){
                            type=NavType.StringType
                        }
                    )){
                        val cryptoId=remember{
                            it.arguments?.getString("cryptoId")
                        }
                        val cryptoPrice=remember{
                            it.arguments?.getString("cryptoPrice")
                        }
                        CryptoDetailScreen(id = cryptoId ?: "", price = cryptoPrice?: "", navController = navController)
                    }
                }


            }
        }
    }
}

