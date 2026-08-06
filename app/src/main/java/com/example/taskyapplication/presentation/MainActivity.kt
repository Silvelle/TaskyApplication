package com.example.taskyapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.navigation.Navigator
import com.example.navigation.rememberNavigationState
import com.example.taskyapplication.presentation.navigation.BottomNavigationBar
import com.example.taskyapplication.presentation.navigation.Home
import com.example.taskyapplication.presentation.navigation.TOP_LEVEL_DESTINATIONS
import com.example.taskyapplication.presentation.ui.theme.TaskyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskyApplicationTheme {
                val navigationState = rememberNavigationState(
                    startKey = Home,
                    topLevelKeys = TOP_LEVEL_DESTINATIONS.keys,
                )
                val navigator = remember(navigationState) {
                    Navigator(navigationState)
                }


                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            selectedKey = navigationState.currentTopLevelKey,
                            onSelectKey = navigator::navigate,
                        )
                    }
                ) { innerPadding ->
                    SomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}