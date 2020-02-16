package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables

class SimpleTool(private val path: String, private val ratio: Float, val fixing: DeviceStatus)  {
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
    var sfx: Sound = Gdx.audio.newSound(Gdx.files.internal("$path/sfx.mp3"))

    init{
        loadImage()
    }

    fun relocateCentre(x: Float, y: Float){
        centreX = x
        centreY = y
    }

    fun getSprite(): Sprite {
        return idleSprite
    }

    fun flyingCentre(x: Float, y:Float){
        flyingCentreX = x
        flyingCentreY = SharedVariables.mainHeight - y
    }

    private fun loadImage(){
        idleSprite = SharedVariables.loadSprite("$path/idle.png", ratio.toDouble())
        glowSprite = SharedVariables.loadSprite("$path/glow.png", ratio.toDouble())
        flyingSprite = SharedVariables.loadSprite("$path/idle.png", ratio.toDouble()/2)
        chosenSprite = idleSprite
    }

    fun draw(batch: SpriteBatch){
        chosenSprite = when(status){
            ToolStatus.GLOW -> glowSprite
            else -> idleSprite
        }
        chosenSprite.setCenter(centreX,centreY)

        if (ToolStatus.INACTIVE!=status){
            chosenSprite.draw(batch)
            if (flying){
                flyingSprite.setCenter(flyingCentreX,flyingCentreY)
                flyingSprite.draw(batch)
            }
        } else {
            flying = false
        }


    }
}