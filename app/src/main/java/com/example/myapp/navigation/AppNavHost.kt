package com.example.myapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.ui.theme.screens.client.AddClientScreen
import com.example.myapp.ui.theme.screens.client.UpdateClientScreen
import com.example.myapp.ui.theme.screens.client.ViewClients
import com.example.myapp.ui.theme.screens.dashboard.DashBoard
import com.example.myapp.ui.theme.screens.login.LoginScreen
import com.example.myapp.ui.theme.screens.signup.SignupScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(),
               startDestination:String = ROUTE_HOME){
        NavHost(navController = navController,
            startDestination = startDestination ){
            composable(ROUTE_REGISTER){ SignupScreen(navController)}
            composable(ROUTE_LOGIN){ LoginScreen(navController)}
            composable(ROUTE_HOME){ DashBoard(navController) }
            composable(ROUTE_ADD_CLIENT){ AddClientScreen(navController) }
            composable(ROUTE_VIEW_CLIENT){ ViewClients(navController) }
            composable("$ROUTE_UPDATE_CLIENT/{id}") {
                passedData -> UpdateClientScreen(
                navController, passedData.arguments?.getString("id")!! )
            }

        }
}