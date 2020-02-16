package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables.loadSprite
import com.pungo.repairgame.Timer

class SimpleDevice(private val path: String, private val ratio: Float) {
    private lateinit var normalSprite: Sprite
    private lateinit var deadSprite: Sprite
    private lateinit var brokenSprite: Sprite
    private lateinit var hotSprite: Sprite
    private lateinit var stuckSprite: Sprite
    private lateinit var izelSprite: Sprite
    private lateinit var chosenSprite: Sprite
    private var centreX = 0f
    private var centreY = 0f
    var status = DeviceStatus.NORMAL
    val breakTimer = Timer(5000)

    init{
        loadImage()
    }

    fun getSprite(): Sprite {
        return normalSprite
    }

    private fun loadImage(){
        normalSprite = loadSprite("$path/normal.png", ratio.toDouble())
        deadSprite = loadSprite("$path/dead.png", ratio.toDouble())
        brokenSprite = loadSprite("$path/broken.png", ratio.toDouble())
        hotSprite = loadSprite("$path/hot.png", ratio.toDouble())
        stuckSprite = loadSprite("$path/stuck.png", ratio.toDouble())
        izelSprite = loadSprite("$path/izel.png", ratio.toDouble())
        chosenSprite = normalSprite
    }


    fun relocateCentre(x: Float, y: Float){
        centreX = x
        centreY = y
    }

    fun breakDevice(s: DeviceStatus){
        status = s
        breakTimer.go()
        breakTimer.running = true
    }

    fun checkTimer(){
        if (breakTimer.running) {
                if (breakTimer.done()) {
                    breakTimer.running = false
                    status = DeviceStatus.DEAD
                }
        }
    }

    private fun recentreBoys(){
        normalSprite.setCenter(centreX,centreY)
        hotSprite.setCenter(centreX,centreY)
        brokenSprite.setCenter(centreX,centreY)
        stuckSprite.setCenter(centreX,centreY)
        izelSprite.setCenter(centreX,centreY)
        deadSprite.setCenter(centreX,centreY)
    }

    fun draw(batch: SpriteBatch){
        recentreBoys()

        when(status){
            DeviceStatus.DEAD -> deadSprite.draw(batch)
            else -> normalSprite.draw(batch)
        }
        when(status){
            DeviceStatus.HOT -> hotSprite.draw(batch)
            DeviceStatus.BROKEN -> brokenSprite.draw(batch)
            DeviceStatus.STUCK -> stuckSprite.draw(batch)
            DeviceStatus.SHORT -> izelSprite.draw(batch)
            else -> {}
        }
    }
}