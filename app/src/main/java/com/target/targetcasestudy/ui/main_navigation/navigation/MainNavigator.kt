package com.target.targetcasestudy.ui.main_navigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.target.targetcasestudy.ui.deal_listing.DealListScreen
import com.target.targetcasestudy.ui.deal_detail.DealDetailScreen

@Composable
fun MainNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = DealListScreen) {
        composable<DealListScreen> {
            DealListScreen(modifier = Modifier,
                moveToDetailScreen = {
                    navController.navigate(DealDetailScreen(it))
                })
        }

        composable<DealDetailScreen> {
            DealDetailScreen(onBackPressed = {
                navController.navigateUp()
            })

        }
    }
}