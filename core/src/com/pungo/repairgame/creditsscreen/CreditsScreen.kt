package com.pungo.repairgame.creditsscreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.pungo.repairgame.AnimationHandler
import com.pungo.repairgame.Screen
import com.pungo.repairgame.SharedVariables
import com.pungo.repairgame.SharedVariables.loadSprite

class CreditsScreen : Screen() {
    private lateinit var bgSprite: Sprite
    private var done = true
    private var creditsAnimation = AnimationHandler().also{
        it.relocateCentre(SharedVariables.monitorCentreX,SharedVariables.monitorCentreY)
    }

    override fun lateInitializer() {
        bgSprite = loadSprite(SharedVariables.creditsPath, SharedVariables.creditsRatio)
        bgSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        bgSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        creditsAnimation.lateInitializer(0.1f, TextureAtlas(Gdx.files.internal(SharedVariables.creditsAnimPath)).regions)
    }

    override fun loopAction() {
        if (done){
            creditsAnimation.animationGo()
            done = false
        }
    }

    override fun draw(batch: SpriteBatch) {
        if (!done) {
            creditsAnimation.draw(batch)
        }
        bgSprite.draw(batch)
        if(creditsAnimation.isDone()){
            done = true
            SharedVariables.activeScreen = SharedVariables.mainMenuScreen
        }
    }

    override fun firstPress() {
    }

    override fun pressing() {
    }

    override fun released() {
    }
}