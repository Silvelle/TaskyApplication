package com.example.taskyapplication.presentation.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey

data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val TOP_LEVEL_DESTINATIONS = mapOf(
    Home to BottomNavItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    Tasks to BottomNavItem(
        title = "Tasks",
        selectedIcon = Icons.Filled.Task,
        unselectedIcon = Icons.Outlined.Task
    ),
    Notes to BottomNavItem(
        title = "Notes",
        selectedIcon = Icons.Filled.FileCopy,
        unselectedIcon = Icons.Outlined.FileCopy
    ),
    Calendar to BottomNavItem(
        title = "Calendar",
        selectedIcon = Icons.Filled.CalendarMonth,
        unselectedIcon = Icons.Outlined.CalendarMonth
    ),
    Profile to BottomNavItem(
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )
)