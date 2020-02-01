package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.pungo.repairgame.gamescreen.GameScreen
import com.pungo.repairgame.loadingscreen.LoadingScreen
import com.pungo.repairgame.mainmenu.MainMenuScreen


object SharedVariables{
    const val mainWidth = 1920
    const val mainHeight = 1080
    const val companyLogoRatio = 0.9 //1 is fullsize, 2 is double size 0.5 is half size... MATH...
    const val menuBackgroundRatio = 0.5
    const val gameBackgroundRatio = 0.25
    const val letterRevealSpeed = 0.2f //letter per render
    const val companyLogoPath = "graphics/pungotitle.png"
    const val mainMenuBackgroundPath = "graphics/main_menu_placeholder.png"
    const val gameBackgroundPath = "graphics/game_placeholder.png"

    var loadingScreen = LoadingScreen()
    var mainMenuScreen = MainMenuScreen()
    var gameScreen = GameScreen()
    var activeScreen : Screen = loadingScreen
    var mouse = MouseManager()

    fun loadSprite(path: String, ratio: Double): Sprite {
        val pixmap = Pixmap(Gdx.files.internal(path))
        val pixmap2 = Pixmap((pixmap.width * ratio).toInt(), (pixmap.height * ratio).toInt(), pixmap.format)
        pixmap2.filter = Pixmap.Filter.NearestNeighbour
        pixmap2.blending = Pixmap.Blending.None
        pixmap2.drawPixmap(pixmap, 0, 0, pixmap.width, pixmap.height, 0, 0, pixmap2.width, pixmap2.height)
        pixmap.dispose()

        return Sprite(Texture(pixmap2)).also { pixmap2.dispose() }
    }

    fun contains(x: Float, y: Float, chosenSprite: Sprite): Boolean {
        val yCorr = mainHeight - y
        val xContains = (x > chosenSprite.x) && (x < (chosenSprite.x + chosenSprite.width))
        val yContains = (yCorr > chosenSprite.y) && (yCorr < (chosenSprite.y + chosenSprite.height))
        return xContains && yContains
    }

}