package com.pungo.repairgame.gamescreen.devices

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables
import com.pungo.repairgame.gamescreen.devices.DeviceGraphics

class Gen1Graphics(path: String, ratio: Float) : DeviceGraphics() {
    private var normalSprite: Sprite = SharedVariables.loadSprite("$path/normal.png", ratio.toDouble())
    private var deadSprite: Sprite = SharedVariables.loadSprite("$path/dead.png", ratio.toDouble())
    private var brokenSprite: Sprite = SharedVariables.loadSprite("$path/broken.png", ratio.toDouble())
    private var hotSprite: Sprite = SharedVariables.loadSprite("$path/hot.png", ratio.toDouble())
    private var stuckSprite: Sprite = SharedVariables.loadSprite("$path/stuck.png", ratio.toDouble())
    private var izelSprite: Sprite = SharedVariables.loadSprite("$path/izel.png", ratio.toDouble())

    override fun getNormalSprite(): Sprite {
        return normalSprite
    }

    override fun recentre(centreX: Float, centreY: Float) {
        normalSprite.setCenter(centreX,centreY)
        hotSprite.setCenter(centreX,centreY)
        brokenSprite.setCenter(centreX,centreY)
        stuckSprite.setCenter(centreX,centreY)
        izelSprite.setCenter(centreX,centreY)
        deadSprite.setCenter(centreX,centreY)
    }

    override fun drawNormal(batch: SpriteBatch,dead: Boolean){
        if (dead){
            deadSprite.draw(batch)
        } else {
            normalSprite.draw(batch)
        }
    }

    override fun drawHot(batch: SpriteBatch){
        hotSprite.draw(batch)
    }

    override fun drawBroken(batch: SpriteBatch){
        brokenSprite.draw(batch)
    }

    override fun drawStuck(batch: SpriteBatch){
        stuckSprite.draw(batch)
    }

    override fun drawShort(batch: SpriteBatch){
        izelSprite.draw(batch)
    }
}