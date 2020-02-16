package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.pungo.repairgame.ending.EndingScreen
import com.pungo.repairgame.gamescreen.GameScreen
import com.pungo.repairgame.loadingscreen.LoadingScreen
import com.pungo.repairgame.mainmenu.MainMenuScreen


object SharedVariables{
    const val mainWidth = 1920
    const val mainHeight = 1080
    const val companyLogoRatio = 0.5 //1 is fullsize, 2 is double size 0.5 is half size... MATH...
    const val menuBackgroundRatio = 0.5
    const val gameBackgroundRatio = 0.25
    const val monitorRatio = 0.25
    const val letterRevealSpeed = 0.8f //letter per render
    const val companyLogoPath = "graphics/pungotitle.png"
    const val mainMenuBackgroundPath = "graphics/menu_background.png"
    const val titlePath = "graphics/title.png"
    const val gameBackgroundPath = "graphics/game_placeholder.png"
    const val monitorPath = "graphics/space_holder.png"
    const val monitorFramePath = "graphics/monitor_frame.png"
    const val rocketAnimationPath = "gifs/rocket_animation.atlas"
    const val fontAddress = "fonts/PTMono-Regular.ttf"

    const val monitorCentreX = 960f
    const val monitorCentreY = 786f

    var sfxVolume = 1.0f
    var kill = false
    var planets = MutableList(3) { Pair("x", 0) }
    var planetIndex: Int = 0

    init {
        planets[0] = Pair("home", 1)
        planets[1] = Pair("repair", 8)
        planets[2] = Pair("dessert", 43)
    }

    var loadingScreen = LoadingScreen()
    var mainMenuScreen = MainMenuScreen()
    var gameScreen = GameScreen()
    var endingScreen = EndingScreen()
    var activeScreen: Screen = loadingScreen
    var mouse = MouseManager()


    fun loadPixmap(path: String, ratio: Double): Pixmap {
        val pixmap = Pixmap(Gdx.files.internal(path))
        val pixmap2 = Pixmap((pixmap.width * ratio).toInt(), (pixmap.height * ratio).toInt(), pixmap.format)
        pixmap2.filter = Pixmap.Filter.NearestNeighbour
        pixmap2.blending = Pixmap.Blending.None
        pixmap2.drawPixmap(pixmap, 0, 0, pixmap.width, pixmap.height, 0, 0, pixmap2.width, pixmap2.height)
        pixmap.dispose()
        return pixmap2
    }

    fun loadSprite(path: String, ratio: Double): Sprite {
        val pixmap2 = loadPixmap(path,ratio)
        return Sprite(Texture(pixmap2)).also { pixmap2.dispose() }
    }

    fun contains(x: Float, y: Float, chosenSprite: Sprite): Boolean {
        val yCorr = mainHeight - mainHeight*(y/Gdx.graphics.height)
        val xCorr = mainWidth*(x/Gdx.graphics.width)
        val xContains = (xCorr > chosenSprite.x) && (xCorr < (chosenSprite.x + chosenSprite.width))
        val yContains = (yCorr > chosenSprite.y) && (yCorr < (chosenSprite.y + chosenSprite.height))
        return xContains && yContains
    }

}