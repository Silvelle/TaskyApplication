package com.example.taskyapplication.presentation.navigation

import androidx.compose.foundation.magnifier
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey


@Composable
fun BottomNavigationBar(
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit
) {
    BottomAppBar(
    ) {
        TOP_LEVEL_DESTINATIONS.forEach { (toplevelDestination, data) ->
            val isSelected = selectedKey == toplevelDestination
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    onSelectKey(toplevelDestination)
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected)
                            data.selectedIcon
                        else
                            data.unselectedIcon,
                        contentDescription = data.title
                    )
                },
                label = {
                    Text(data.title)
                }
            )
        }
    }
}