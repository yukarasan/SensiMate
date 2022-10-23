package com.example.sensimate

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sensimate.data.BottomNavItem
import com.example.sensimate.model.manropeFamily
import com.example.sensimate.ui.home.HomeScreen
import com.example.sensimate.ui.profile.ProfileScreen
import com.example.sensimate.ui.theme.SensimateTheme

@Composable
fun App(
    // modifier: Modifier = Modifier,
    // appViewModel: AppViewModel = viewModel()
) {
    // val appUiState by appViewModel.uiState.collectAsState()
    SetUpNavigation()
}

//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------

@Composable
private fun SetUpNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = "home",
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Profile",
                        route = "profile",
                        icon = Icons.Default.Person
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Navigation(navController = navController)
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("profile") {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,    //TODO: Create black top border
        backgroundColor = Color(22, 26, 30),
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,     // TODO: Change color
                unselectedContentColor = Color.Red,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                        if (selected) {     // TODO: If selected, show purple circle instead.
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontFamily = manropeFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                            )
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    SensimateTheme {
        App()
    }
}