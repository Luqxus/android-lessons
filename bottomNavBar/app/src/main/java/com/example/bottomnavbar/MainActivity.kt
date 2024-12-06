package com.example.bottomnavbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavbar.ui.theme.BottomNavBarTheme

class MainActivity : ComponentActivity() {

    val bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            name = "home",
            route = "home",
            icon = Icons.Rounded.Home,
            badgeCount = 0
        ),

        BottomNavItem(
            name = "chat",
            route = "chat",
            icon = Icons.Rounded.Notifications,
            badgeCount = 6
        ),

        BottomNavItem(
            name = "settings",
            route = "settings",
            icon = Icons.Rounded.Settings,
            badgeCount = 0
        ),
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavBarTheme {

                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController,
                            items = bottomNavItems,
                            onItemClick = { item ->
                                navController.navigate(item.route)
                            }
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Navigation(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {

    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar {
        BottomAppBar {
            items.forEach {

                val selected = it.route == backStackEntry.value?.destination?.route

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        onItemClick(it)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.secondary,
                        unselectedTextColor = MaterialTheme.colorScheme.secondary
                    ),
                    icon = {
                        if(it.badgeCount > 0) {
                            BadgedBox (
                                badge = {
                                    Text(it.badgeCount.toString())
                                }
                            ) {
                                Icon(it.icon, contentDescription =  null)
                            }
                        } else {
                            Icon(it.icon, contentDescription =  null)
                        }
                    },

                    label = {
                        if (selected) {
                            Text(it.name)
                        } else {
                            Text("")
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen()
        }

        composable("chat") {
            ChatScreen()
        }

        composable("settings") {
            SettingsScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen")
    }
}

@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Chat Screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings Screen")
    }
}