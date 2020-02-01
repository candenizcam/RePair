package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite


object SharedVariables{
    const val mainWidth = 1920
    const val mainHeight = 1080
    const val companyLogoRatio = 0.9 //1 is fullsize, 2 is double size 0.5 is half size... MATH...
    const val gameBackgroundRatio = 0.25
    const val companyLogoPath = "graphics/pungotitle.png"
    const val mainMenuBackgroundPath = "graphics/main_menu_placeholder.png"
    const val gameBackgroundPath = "graphics/game_placeholder.png"
    var leftestDeviceStatus = DeviceStatus.NORMAL

    var loadingScreen = LoadingScreen()
    var mainMenuScreen = MainMenuScreen()
    var gameScreen = GameScreen()
    var activeScreen : Screen = loadingScreen
    var mouse = MouseManager()

    fun loadSprite(path: String, ratio: Double): Sprite {
        var pixmap = Pixmap(Gdx.files.internal(path))
        pixmap = Pixmap((pixmap.width*ratio).toInt(),(pixmap.height*ratio).toInt(),pixmap.format)
        pixmap.filter = Pixmap.Filter.NearestNeighbour
        pixmap.blending = Pixmap.Blending.None
        pixmap.drawPixmap(pixmap, 0,0, pixmap.width,pixmap.height,0,0,pixmap.width,pixmap.height)
        pixmap.dispose()
        return Sprite(Texture(pixmap))
    }
}