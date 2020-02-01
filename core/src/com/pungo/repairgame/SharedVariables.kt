package com.pungo.repairgame


object SharedVariables {
    const val mainWidth = 1920
    const val mainHeight = 1080
    const val companyLogoRatio = 0.9 //1 is fullsize, 2 is double size 0.5 is half size... MATH...
    const val companyLogoPath = "graphics/pungotitle.png"
    const val mainMenuBackgroundPath = "graphics/main_menu_placeholder.png"

    var loadingScreen = LoadingScreen()
    var mainMenuScreen = MainMenuScreen()
    var activeScreen: Screen = loadingScreen
    var mouse = MouseManager()
}