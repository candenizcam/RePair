package com.pungo.repairgame.gamescreen.devices

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Animator(private val images: List<Sprite>, private var looping: Boolean){
    private var counter = 0f
    private var ended = false

    fun reset(isLooping: Boolean = looping){
        looping = isLooping
        ended = false
        counter = 0f
    }

    fun recentre(x: Float, y: Float){
        images.forEach {
            it.setCenter(x,y)
        }
    }

    private fun loopDraw(batch: SpriteBatch,increment: Float){
        counter += increment
        if (counter>=images.size){
            counter %= images.size
        }
        images[counter.toInt()].draw(batch)
    }

    private fun singleDraw(batch: SpriteBatch, increment: Float) {
        if (counter<images.size){
            images[counter.toInt()].draw(batch)
            counter += increment
        } else {
            ended = true
            images[-1].draw(batch)
        }
    }

    fun draw(batch: SpriteBatch,increment: Float=0f){
        if (images.isNotEmpty()){
            if (looping){
                loopDraw(batch,increment)
            } else {
                singleDraw(batch,increment)
            }
        }

    }

    fun getSprite(): Sprite {
        return images[0]
    }
}