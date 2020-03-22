package com.pungo.repairgame.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables
import java.lang.Double.max
import java.lang.Double.min

class Slider(private val path: String, private val ratio: Float) {
    private val railSprite: Sprite
    private val ballSprite: Sprite
    private var centreX = 0f
    private var centreY = 0f
    var sliderValue = 1f //between 0 and 1
    private var sliderRecord = 0f //between 0 and 1
    var visible = true
    private var moving = false

    init{
        railSprite = SharedVariables.loadSprite("$path/rail.png", ratio.toDouble())
        ballSprite = SharedVariables.loadSprite("$path/ball.png", ratio.toDouble())
    }

    fun relocateCentre(x: Float, y: Float) {
        centreX = x
        centreY = y
    }

    fun draw(batch: SpriteBatch) {
        if (visible){
            railSprite.setCenter(centreX,centreY)
            railSprite.draw(batch)
            val ballPosition = centreX-(0.5f-sliderValue)*(railSprite.width-ballSprite.width*0.5f)
            ballSprite.setCenter(ballPosition,centreY)
            ballSprite.draw(batch)
        }

    }

    fun firstPressed(){
        if(SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), railSprite)){
            moving = true
        }
    }

    fun pressing(){
        if (moving){
            val xCorr = SharedVariables.mainWidth *(Gdx.input.x.toFloat()/Gdx.graphics.width)
            val ballTarget = max(0.0,min(1.0,(xCorr-centreX)/(railSprite.width-ballSprite.width*0.5f)+0.5))
            sliderValue = ballTarget.toFloat()
        }
    }

    fun released(){
        moving=false
    }


}