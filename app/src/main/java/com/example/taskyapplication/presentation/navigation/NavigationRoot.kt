package com.example.taskyapplication.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.taskyapplication.presentation.DatabaseTestScreen
import com.example.taskyapplication.presentation.calendar.CalendarScreen
import com.example.taskyapplication.presentation.home.HomeScreen
import com.example.taskyapplication.presentation.profile.ProfileScreen
import com.example.taskyapplication.presentation.tasks.TasksScreen

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Home)

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(
                selectedKey = Home,
                onSelectKey = {
                    // TODO
                }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<Home>
                {
                    HomeScreen()
                }
                entry<Tasks>
                {
                    TasksScreen()
                }
                entry<Notes>
                {
                    DatabaseTestScreen()
                }
                entry<Calendar> {
                    CalendarScreen()
                }
                entry<Profile> {
                    ProfileScreen()
                }

            }
        )
    }
}