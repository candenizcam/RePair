package com.pungo.repairgame

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.Array

class AnimationHandler{
    private var animationLooping = false
    private lateinit var animation : Animation<TextureAtlas.AtlasRegion>
    private var animTimer = Timer(0)
    private var animationDone = true
    private var hideWhenDone = true
    private var centreX = 0f
    private var centreY = 0f
    private var width = 0f
    private var height = 0f


    fun relocateCentre(x: Float, y: Float){
        centreX = x
        centreY = y
    }


    fun lateInitializer(frameDuration: Float=1f/24, textureAtlasRegion: Array<TextureAtlas.AtlasRegion>){
        animation = Animation(frameDuration,textureAtlasRegion)
        width = textureAtlasRegion[0].originalWidth.toFloat()
        height = textureAtlasRegion[0].originalHeight.toFloat()
    }

    fun animationGo(){
        animationDone = false
        animTimer.go()
    }

    fun isDone(): Boolean {
        return animationDone
    }

    fun draw(batch: SpriteBatch){
        if (!hideWhenDone || !animationDone){
            batch.draw(animation.getKeyFrame(animTimer.now()/1000f, animationLooping), centreX-width/2, centreY-height/2)
        }
        animationDone = animTimer.now()/1000f > animation.animationDuration
    }

}