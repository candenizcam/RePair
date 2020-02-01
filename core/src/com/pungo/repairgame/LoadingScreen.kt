package com.pungo.repairgame

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables.loadSprite

class LoadingScreen() : Screen() {
    private lateinit var bgSprite: Sprite
    private lateinit var bgPixmap: Pixmap
    private lateinit var menuSprite: Sprite
    private var menuVisible = false
    lateinit var timer: Timer

    override fun lateInitializer() {
        loadImage()
        timer = Timer(3000)
    }

    private fun loadImage() {
        bgSprite = loadSprite(SharedVariables.companyLogoPath, SharedVariables.companyLogoRatio)
        bgSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        bgSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)

        menuSprite = loadSprite(SharedVariables.mainMenuBackgroundPath, SharedVariables.gameBackgroundRatio)
        menuSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        menuSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)
    }

    override fun loopAction() {
        when {
            timer.now() < 1000 -> {
                menuVisible = false
                bgSprite.setAlpha(timer.now().toFloat() / 1000)
            }
            timer.now() < 2000 -> {
                bgSprite.setAlpha(1f)
            }
            timer.now() < 3000 -> {
                menuVisible = true
                bgSprite.setAlpha(3f - timer.now().toFloat() / 1000)
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