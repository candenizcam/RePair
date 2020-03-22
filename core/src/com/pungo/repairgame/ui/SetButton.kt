package com.pungo.repairgame.ui

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables.loadSprite
import com.pungo.repairgame.ui.ButtonStatus

open class SetButton(path: String, ratio: Float) {
    protected var upSprite = loadSprite("$path/up.png", ratio.toDouble())
    protected var downSprite = loadSprite("$path/down.png", ratio.toDouble())
    var activeSprite: Sprite
    var status = ButtonStatus.UP
    var visible = true
    protected var centreX = 0f
    protected var centreY = 0f

    init {
        activeSprite = upSprite
    }



    fun relocateCentre(x: Float, y: Float) {
        centreX = x
        centreY = y
    }

    open fun draw(batch: SpriteBatch) {
        if (visible){
            activeSprite = when(status){
                ButtonStatus.UP -> upSprite
                ButtonStatus.DOWN -> downSprite
            }
            activeSprite.setCenter(centreX,centreY)
            activeSprite.draw(batch)
        }

    }

    fun getBoundSprite(): Sprite {
        return activeSprite
    }

}