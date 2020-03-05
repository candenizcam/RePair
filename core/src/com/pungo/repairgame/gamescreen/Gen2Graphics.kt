package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.pungo.repairgame.AnimationHandler
import com.pungo.repairgame.SharedVariables

class Gen2Graphics(path: String, ratio: Float) : DeviceGraphics() {
    // private var izelAnimation = AnimationHandler()
    // private var hotAnimation = AnimationHandler()
    // private var stuckAnimation = AnimationHandler()
    private var izelAnimation: List<Sprite>
    private var hotAnimation: List<Sprite>
    private var stuckAnimation: List<Sprite>
    private var brokenSprite: Sprite
    private var normalSprite: Sprite
    private var deadSprite: Sprite
    private var animationCounter = 0.0

    init {
        var atlas = TextureAtlas(Gdx.files.internal("$path/device.atlas"))
        izelAnimation = atlas.createSprites("izel").toList()
        hotAnimation = atlas.createSprites("hot").toList()
        stuckAnimation = atlas.createSprites("stuck").toList()
        //izelAnimation.lateInitializer(1f/4,atlas.findRegions("izel"))
        //hotAnimation.lateInitializer(1f/4,atlas.findRegions("hot"))
        //stuckAnimation.lateInitializer(1f/4,atlas.findRegions("stuck"))
        brokenSprite = atlas.createSprite("broken")
        normalSprite = atlas.createSprite("working")
        deadSprite = atlas.createSprite("working")

        deadSprite.color = Color.DARK_GRAY
    }

    override fun getNormalSprite(): Sprite {
        return normalSprite
    }

    override fun recentre(centreX: Float, centreY: Float){
        normalSprite.setCenter(centreX,centreY)
        hotAnimation.forEach {
            it.setCenter(centreX,centreY)
        }
        stuckAnimation.forEach {
            it.setCenter(centreX,centreY)
        }
        izelAnimation.forEach {
            it.setCenter(centreX,centreY)
        }
        brokenSprite.setCenter(centreX,centreY)
        deadSprite.setCenter(centreX,centreY)
    }


    override fun drawHot(batch: SpriteBatch){
        animationCounter %= 4
        hotAnimation[animationCounter.toInt()].draw(batch)
        animationCounter += 0.1
    }

    override fun drawStuck(batch: SpriteBatch){
        animationCounter %= 4
        stuckAnimation[animationCounter.toInt()].draw(batch)
        if (animationCounter<3){
            animationCounter += 0.01
        }
    }

    override fun drawShort(batch: SpriteBatch){
        animationCounter %= 4
        izelAnimation[animationCounter.toInt()].draw(batch)
        animationCounter += 0.5
    }

    override fun drawNormal(batch: SpriteBatch){
        normalSprite.draw(batch)
    }

    override fun drawBroken(batch: SpriteBatch){
        brokenSprite.draw(batch)
    }

    override fun drawDead(batch: SpriteBatch){
        deadSprite.draw(batch)
    }


}