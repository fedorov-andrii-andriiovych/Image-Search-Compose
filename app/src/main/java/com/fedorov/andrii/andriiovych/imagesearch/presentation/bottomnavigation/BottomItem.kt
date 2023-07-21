package com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation

import com.fedorov.andrii.andriiovych.imagesearch.R


sealed class BottomItem(
    val title: String,
    val iconId: Int,
    val route: String
) {
    object Screen1 : BottomItem("Main", R.drawable.icon_home, SCREEN_MAIN)
    object Screen2 : BottomItem("Favorites", R.drawable.icon_star_full, SCREEN_FAVORITES)
    object Screen3 : BottomItem("Settings", R.drawable.icon_settings, SCREEN_SETTINGS)

    companion object {
        const val SCREEN_MAIN = "screenMain"
        const val SCREEN_FAVORITES = "screenFavorites"
        const val SCREEN_SETTINGS = "screenSettings"
        const val SCREEN_DETAILED = "screenDetailed"
    }
}
