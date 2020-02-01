package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables.loadSprite

class SimpleButton(private val path: String, private val ratio: Float) {
    private lateinit var upSprite: Sprite
    private lateinit var downSprite: Sprite
    var pressed = false

    init {
        loadImage()
    }

    private fun loadImage() {
        upSprite = loadSprite("$path/up.png", ratio.toDouble())
        downSprite = loadSprite("$path/down.png", ratio.toDouble())
    }

    fun relocateCentre(x: Float, y: Float) {
        upSprite.setCenterX(x)
        upSprite.setCenterY(y)
        downSprite.setCenterX(x)
        downSprite.setCenterY(y)

    }

    fun draw(batch: SpriteBatch) {
        if (pressed) {
            downSprite.draw(batch)
        } else {
            upSprite.draw(batch)
        }
    }

    fun contains(x: Float, y: Float): Boolean {
        val y_corr = SharedVariables.mainHeight - y
        val xContains = (x > upSprite.x) && (x < (upSprite.x + upSprite.width))
        val yContains = (y_corr > upSprite.y) && (y_corr < (upSprite.y + upSprite.height))
        return xContains && yContains
    }


}