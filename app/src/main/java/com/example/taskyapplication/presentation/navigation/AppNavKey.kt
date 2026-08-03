package com.example.taskyapplication.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
sealed interface AppNavKey : NavKey

// Top level destinations
@Serializable
data object Home : AppNavKey

@Serializable
data object Tasks : AppNavKey

@Serializable
data object Calendar : AppNavKey

@Serializable
data object Profile : AppNavKey

@Serializable
data object Habits : AppNavKey
