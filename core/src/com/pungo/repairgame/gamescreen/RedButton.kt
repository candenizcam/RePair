package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables
import com.pungo.repairgame.ui.ButtonStatus
import com.pungo.repairgame.ui.SetButton

class RedButton(path: String, ratio: Float): SetButton(path, ratio) {
    private var glowSprite = SharedVariables.loadSprite("$path/glow.png", ratio.toDouble())
    private var bgSprite = SharedVariables.loadSprite("$path/bg.png", ratio.toDouble())
    var glowing = false

    override fun draw(batch: SpriteBatch) {
        bgSprite.setCenter(centreX, centreY)
        bgSprite.draw(batch)

        if (visible) {
            activeSprite = when (status) {
                ButtonStatus.UP -> {
                    if (glowing) {
                        glowSprite
                    } else {
                        upSprite
                    }
                }
                ButtonStatus.DOWN -> downSprite
            }
            activeSprite.setCenter(centreX, centreY)
            activeSprite.draw(batch)
        }
    }
}