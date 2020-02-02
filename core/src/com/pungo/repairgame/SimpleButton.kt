package com.pungo.repairgame

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables.loadSprite

class SimpleButton(private val path: String, private val ratio: Float) {
    private lateinit var upSprite: Sprite
    private lateinit var downSprite: Sprite
    lateinit var activeSprite: Sprite
    var status = ButtonStatus.UP
    private var centreX = 0f
    private var centreY = 0f

    init {
        loadImage()
    }

    private fun loadImage() {
        upSprite = loadSprite("$path/up.png", ratio.toDouble())
        downSprite = loadSprite("$path/down.png", ratio.toDouble())
        activeSprite = upSprite
    }

    fun relocateCentre(x: Float, y: Float) {
        centreX = x
        centreY = y
    }

    fun draw(batch: SpriteBatch) {
        activeSprite = when(status){
            ButtonStatus.UP -> upSprite
            ButtonStatus.DOWN -> downSprite
        }
        activeSprite.setCenter(centreX,centreY)
        activeSprite.draw(batch)
    }
}