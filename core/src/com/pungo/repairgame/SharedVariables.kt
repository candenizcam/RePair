package com.pungo.repairgame



object SharedVariables{
    val mainWidth = 1920
    val mainHeight = 1080
    val companyLogoRatio = 0.9 //1 is fullsize, 2 is double size 0.5 is half size... MATH...
    val companyLogoPath = "graphics/pungotitle.png"
    val mainMenuBackgroundPath = "graphics/main_menu_placeholder.png"

    var loadingScreen = LoadingScreen()
    var mainMenuScreen = MainMenuScreen()
    var activeScreen : Screen = loadingScreen
    var mouse = MouseManager(activeScreen)
}