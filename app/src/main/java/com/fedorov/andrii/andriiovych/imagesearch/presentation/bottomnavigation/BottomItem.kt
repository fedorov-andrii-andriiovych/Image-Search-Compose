package com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation

import com.fedorov.andrii.andriiovych.imagesearch.R


sealed class BottomItem(
    val title: String,
    val iconId: Int,
    val route: String
) {
    object Screen1 : BottomItem("Main", R.drawable.icon_home, Screens.MAIN.route)
    object Screen2 : BottomItem("Favorites", R.drawable.icon_star_full, Screens.FAVORITE.route)
    object Screen3 : BottomItem("Settings", R.drawable.icon_settings, Screens.SETTINGS.route)
}

enum class Screens(val route:String){
    MAIN("screenMain"),
    FAVORITE("screenFavorites"),
    SETTINGS("screenSettings"),
    DETAILED("screenDetailed")
}
