package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables.loadSprite

class SimpleDevice(private val path: String, private val ratio: Float) {
    private lateinit var normalSprite: Sprite
    private lateinit var brokenSprite: Sprite
    private lateinit var hotSprite: Sprite
    lateinit var chosenSprite: Sprite
    private var centreX = 0f
    private var centreY = 0f
    var status = DeviceStatus.NORMAL

    init{
        loadImage()
    }

    private fun loadImage(){
        normalSprite = loadSprite("$path/normal.png", ratio.toDouble())
        brokenSprite = loadSprite("$path/broken.png", ratio.toDouble())
        hotSprite = loadSprite("$path/hot.png", ratio.toDouble())
        chosenSprite = normalSprite
    }


    fun relocateCentre(x: Float, y: Float){
        centreX = x
        centreY = y
    }


    fun draw(batch: SpriteBatch){
        chosenSprite = when(status){
            DeviceStatus.NORMAL -> normalSprite
            DeviceStatus.BROKEN -> brokenSprite
            DeviceStatus.HOT -> hotSprite
        }
        chosenSprite.setCenter(centreX,centreY)
        chosenSprite.draw(batch)

    }
}