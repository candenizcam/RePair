package com.pungo.repairgame.loadingscreen

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.Screen
import com.pungo.repairgame.SharedVariables
import com.pungo.repairgame.SharedVariables.loadSprite
import com.pungo.repairgame.Timer
import java.lang.Long.max

class LoadingScreen: Screen() {
    private lateinit var bgSprite: Sprite
    private lateinit var menuSprite: Sprite
    private var menuVisible = false
    lateinit var timer: Timer
    private var loaded = false
    private var loading_time = 0L

    override fun lateInitializer() {
        bgSprite = loadSprite(SharedVariables.companyLogoPath, SharedVariables.companyLogoRatio)
        bgSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        bgSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)

        menuSprite = loadSprite(SharedVariables.mainMenuBackgroundPath, SharedVariables.menuBackgroundRatio)
        menuSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        menuSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        timer = Timer(3000)
    }

    override fun loopAction() {
        when {
            timer.now() < 1000 -> {
                menuVisible = false
                bgSprite.setAlpha(timer.now().toFloat() / 1000)
            }
            timer.now() < 2000 -> {

                if (!loaded){
                    loading_time = timer.now()
                    SharedVariables.mainMenuScreen.lateInitializer()
                    SharedVariables.gameScreen.lateInitializer()
                    SharedVariables.endingScreen.lateInitializer()
                    SharedVariables.creditsScreen.lateInitializer()
                    SharedVariables.optionsScreen.lateInitializer()
                    loading_time = timer.now()-loading_time
                    loaded = true
                }
                bgSprite.setAlpha(1f)
                loading_time = max(loading_time,1000L)
            }
            timer.now() < (2000+loading_time) -> {
                menuVisible = true
                bgSprite.setAlpha(2f + loading_time/1000f - timer.now().toFloat() / 1000)
            }
            else -> {
                SharedVariables.activeScreen = SharedVariables.mainMenuScreen
            }
        }
    }

    override fun draw(batch: SpriteBatch) {
        if (menuVisible) {
            menuSprite.draw(batch)
        }
        bgSprite.draw(batch)
    }

    override fun firstPress() {
    }

    override fun pressing() {
    }

    override fun released() {
    }

    fun isLoading(): Boolean {
        if (timer.done()) {
            return false
        }
        return true
    }
}