package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables
import com.pungo.repairgame.SharedVariables.loadSprite

class SimpleDevice(private val path: String, private val ratio: Float) {
    private lateinit var normalSprite: Sprite
    private lateinit var brokenSprite: Sprite
    private lateinit var hotSprite: Sprite
    private lateinit var stuckSprite: Sprite
    private lateinit var chosenSprite: Sprite
    private var centreX = 0f
    private var centreY = 0f
    var status = DeviceStatus.NORMAL

    init{
        loadImage()
    }

    fun getSprite(): Sprite {
        return normalSprite
    }

    private fun loadImage(){
        normalSprite = loadSprite("$path/normal.png", ratio.toDouble())
        brokenSprite = loadSprite("$path/broken.png", ratio.toDouble())
        hotSprite = loadSprite("$path/hot.png", ratio.toDouble())
        stuckSprite = loadSprite("$path/stuck.png", ratio.toDouble())
        chosenSprite = normalSprite
    }


    fun relocateCentre(x: Float, y: Float){
        centreX = x
        centreY = y
    }


    private fun recentreBoys(){
        normalSprite.setCenter(centreX,centreY)
        hotSprite.setCenter(centreX,centreY)
        brokenSprite.setCenter(centreX,centreY)
        stuckSprite.setCenter(centreX,centreY)
    }

    fun draw(batch: SpriteBatch){
        recentreBoys()

        when(status){
            DeviceStatus.DEAD -> normalSprite.draw(batch)
            else -> normalSprite.draw(batch)
        }
        when(status){
            DeviceStatus.HOT -> hotSprite.draw(batch)
            DeviceStatus.BROKEN -> brokenSprite.draw(batch)
            DeviceStatus.STUCK -> brokenSprite.draw(batch)
            else -> {}
        }
    }
}