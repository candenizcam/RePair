package com.pungo.repairgame.gamescreen.devices

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas

class Gen2Graphics(path: String, ratio: Float) : DeviceGraphics() {
    private var izelAnimation: Animator
    private var hotAnimation: Animator
    private var stuckAnimation: Animator
    private var brokenAnimation: Animator
    private var normalAnimation: Animator
    private var deadAnimation: Animator
    private var overAnimation: Animator
    private var overDeadAnimation: Animator

    init {
        var atlas = TextureAtlas(Gdx.files.internal("$path/device.atlas"))
        izelAnimation = Animator(atlas.createSprites("izel").toList(),true)
        hotAnimation = Animator(atlas.createSprites("hot").toList(),true)
        stuckAnimation = Animator(atlas.createSprites("stuck").toList(),false)
        brokenAnimation = Animator(atlas.createSprites("broken").toList(),false)
        normalAnimation = Animator(atlas.createSprites("working").toList(),false)
        var w = atlas.createSprite("working")
        w.color = Color.DARK_GRAY
        deadAnimation = Animator(listOf(w),false)
        overAnimation = Animator(atlas.createSprites("overframe").toList(),false)
        var w2 = atlas.createSprite("overframe")
        if (w2!=null){
            w2.color = Color.DARK_GRAY
            overDeadAnimation = Animator(listOf(w2),false)
        } else {
            overDeadAnimation = Animator(listOf(),false)
        }

    }

    override fun getNormalSprite(): Sprite {
        return normalAnimation.getSprite()
    }

    override fun reset(){
        normalAnimation.reset()
        hotAnimation.reset()
        stuckAnimation.reset()
        izelAnimation.reset()
        brokenAnimation.reset()
        deadAnimation.reset()
        overAnimation.reset()
        overDeadAnimation.reset()
    }

    override fun recentre(centreX: Float, centreY: Float){
        normalAnimation.recentre(centreX,centreY)
        hotAnimation.recentre(centreX,centreY)
        stuckAnimation.recentre(centreX,centreY)
        izelAnimation.recentre(centreX,centreY)
        brokenAnimation.recentre(centreX,centreY)
        deadAnimation.recentre(centreX,centreY)
        overAnimation.recentre(centreX,centreY)
        overDeadAnimation.recentre(centreX,centreY)
    }


    override fun drawHot(batch: SpriteBatch){
        hotAnimation.draw(batch,0.1f)
    }

    override fun drawStuck(batch: SpriteBatch){
        stuckAnimation.draw(batch,0.1f)
    }

    override fun drawShort(batch: SpriteBatch){
        izelAnimation.draw(batch,0.5f)
    }

    override fun drawNormal(batch: SpriteBatch, dead: Boolean){
        if (dead){
            deadAnimation.draw(batch,0f)
        } else {
            normalAnimation.draw(batch,0f)
        }

    }

    override fun drawBroken(batch: SpriteBatch){
        brokenAnimation.draw(batch,0f)
    }

    override fun drawOver(batch: SpriteBatch, dead: Boolean){
        if (dead){
            overDeadAnimation.draw(batch,increment = 0f)
        } else {
            overAnimation.draw(batch,increment = 0f)
        }

    }
}