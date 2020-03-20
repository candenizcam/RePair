package com.pungo.repairgame.gamescreen.devices

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.Timer
import com.pungo.repairgame.gamescreen.DeviceStatus

open class SimpleDevice(path: String,ratio: Float, gen2Graphics: Boolean = false) {
    private var centreX = 0f
    private var centreY = 0f
    private var graphics : DeviceGraphics = if (gen2Graphics){
        Gen2Graphics(path, ratio)
    } else {
        Gen1Graphics(path, ratio)
    }
    var status = DeviceStatus.NORMAL
    val breakTimer = Timer(5000)
    private var oldStatus = status

    fun getSprite(): Sprite {
        return graphics.getNormalSprite()
    }

    fun relocateCentre(x: Float, y: Float){
        centreX = x
        centreY = y
    }

    fun breakDevice(s: DeviceStatus){
        status = s
        breakTimer.go()
    }

    fun checkTimer(): Boolean{
        if (breakTimer.running) {
            if (breakTimer.done()) {
                breakTimer.running = false
                status = DeviceStatus.DEAD
                return true
            }
        }
        return false
    }

    fun draw(batch: SpriteBatch){
        graphics.recentre(centreX,centreY)
        if (status != oldStatus){
            graphics.reset()
            oldStatus = status
        }
        when(status){
            DeviceStatus.DEAD -> graphics.drawDead(batch)
            else -> graphics.drawNormal(batch)
        }
        when(status){
            DeviceStatus.HOT -> graphics.drawHot(batch)
            DeviceStatus.BROKEN -> graphics.drawBroken(batch)
            DeviceStatus.STUCK -> graphics.drawStuck(batch)
            DeviceStatus.SHORT -> graphics.drawShort(batch)
            else -> {}
        }
        graphics.drawOver(batch)
    }
}