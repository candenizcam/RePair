package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables.loadSprite

class ToggleButton(private val path: String, private val ratio: Float) {
    private var spriteList = mutableListOf<Sprite>()
    private var status = 0
    private var centreX = 0f
    private var centreY = 0f

    init {
        loadImage()
    }

    private fun loadImage() {
        for (k in 0..10){
            if (Gdx.files.internal("$path/$k.png").exists()){
                spriteList.add(loadSprite("$path/$k.png", ratio.toDouble()))
            } else {
                break
            }
        }
    }

    fun relocateCentre(x: Float, y: Float) {
        centreX = x
        centreY = y
    }

    fun toggle(): Int {
        status += 1
        if (status==spriteList.size){
            status=0
        }
        return status
    }

    fun draw(batch: SpriteBatch) {
        spriteList[status].setCenter(centreX,centreY)
        spriteList[status].draw(batch)
    }

    fun getBoundSprite(): Sprite {
        return spriteList[0]
    }
}