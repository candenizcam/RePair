package com.pungo.repairgame.mainmenu

import com.badlogic.gdx.graphics.g2d.Sprite
import com.pungo.repairgame.SharedVariables

class Slider(private val path: String, private val ratio: Float) {
    private val sliderSprite: Sprite
    private val ballSprite: Sprite

    init{
        sliderSprite = SharedVariables.loadSprite("$path/slider.png", ratio.toDouble())
        ballSprite = SharedVariables.loadSprite("$path/slider.png", ratio.toDouble())
    }


}