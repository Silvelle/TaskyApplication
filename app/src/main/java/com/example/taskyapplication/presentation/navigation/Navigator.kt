package com.example.taskyapplication.presentation.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(
    val state: NavigationState
) {
    fun navigate(key: NavKey) {
        when (key) {
            state.currentTopLevelKey -> clearSubStack()
            in state.topLevelRoute -> goToTopLevel(key)
            else -> goToKey(key)
        }
    }

    fun goBack() {
        when (state.currentKey) {
            state.StartRoute -> error("Can't go back from starting point")
            in state.topLevelKeys -> {
                state.topLevelRoute.removeLastOrNull()
            }

            else -> {
                state.currentSubStack.removeLastOrNull()
            }
        }
    }

    fun goToKey(key: NavKey) {
        state.currentSubStack.apply {
            remove(key)
            add(key)
        }
    }

    private fun goToTopLevel(key: NavKey) {
        state.topLevelRoute.apply {
            if (key == state.StartRoute) {
                clear()
            } else {
                remove(key)
            }
            add(key)
        }
    }

    fun clearSubStack() {
        state.currentSubStack.run {
            if (size > 1) subList(1, size).clear()
        }
    }
}