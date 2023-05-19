package com.capstoneC23PS274.segar.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector?,
    val screen: Screen?,
    val contentDescription: String?
)