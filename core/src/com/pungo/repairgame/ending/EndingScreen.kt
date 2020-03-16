package com.pungo.repairgame.ending

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.Screen
import com.pungo.repairgame.SharedVariables

class EndingScreen: Screen() {
    private lateinit var yaySprite: Sprite
    private lateinit var naySprite: Sprite
    private lateinit var activeSprite: Sprite


    override fun draw(batch: SpriteBatch) {
        activeSprite.draw(batch)
    }

    override fun firstPress() {
    }

    override fun pressing() {
    }

    override fun released() {
        SharedVariables.activeScreen = SharedVariables.creditsScreen
    }

    override fun loopAction() {
    }

    fun goodEnder(){
        activeSprite = yaySprite
    }

    fun badEnder(){
        activeSprite = naySprite
    }

    override fun lateInitializer() {
        yaySprite = SharedVariables.loadSprite("graphics/good_end.png",0.5)
        naySprite = SharedVariables.loadSprite("graphics/bad_end.png",0.5)
        activeSprite = yaySprite
    }
}