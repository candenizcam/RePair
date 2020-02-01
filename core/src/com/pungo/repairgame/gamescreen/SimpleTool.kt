package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables

class SimpleTool(private val path: String, private val ratio: Float)  {
    private lateinit var idleSprite: Sprite
    private lateinit var glowSprite: Sprite
    private lateinit var flyingSprite: Sprite
    private lateinit var chosenSprite: Sprite
    private var centreX = 0f
    private var centreY = 0f
    private var flyingCentreX = 0f
    private var flyingCentreY = 0f
    var flying = false
    var status = ToolStatus.IDLE

    init{
        loadImage()
    }

    fun contains(x: Float, y: Float): Boolean {
        val y_corr = SharedVariables.mainHeight - y
        val xContains = (x > chosenSprite.x) && (x < (chosenSprite.x + chosenSprite.width))
        val yContains = (y_corr > chosenSprite.y) && (y_corr < (chosenSprite.y + chosenSprite.height))
        return xContains && yContains
    }

    fun relocateCentre(x: Float, y: Float){
        centreX = x
        centreY = y
    }

    fun flyingCentre(x: Float, y:Float){
        flyingCentreX = x
        flyingCentreY = SharedVariables.mainHeight - y
    }

    private fun loadImage(){
        idleSprite = SharedVariables.loadSprite("$path/idle.png", ratio.toDouble())
        glowSprite = SharedVariables.loadSprite("$path/glow.png", ratio.toDouble())
        flyingSprite = SharedVariables.loadSprite("$path/flying.png", ratio.toDouble())
        chosenSprite = idleSprite
    }

    fun draw(batch: SpriteBatch){
        chosenSprite = when(status){
            ToolStatus.GLOW -> glowSprite
            else -> idleSprite
        }
        chosenSprite.setCenter(centreX,centreY)
        chosenSprite.draw(batch)
        if (flying){
            flyingSprite.setCenter(flyingCentreX,flyingCentreY)
            flyingSprite.draw(batch)
        }

    }
}