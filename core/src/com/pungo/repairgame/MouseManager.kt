package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class MouseManager {
    private var pressed = false

    fun clickListener() {
        when {
            Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !pressed -> {
                pressed = true
                SharedVariables.activeScreen.firstPress()
            }
            Gdx.input.isButtonPressed(Input.Buttons.LEFT) && pressed -> {
                SharedVariables.activeScreen.pressing()
            }
            !Gdx.input.isButtonPressed(Input.Buttons.LEFT) && pressed -> {
                pressed = false
                SharedVariables.activeScreen.released()
            }
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            println("point ${Gdx.input.x} ${Gdx.input.y}")
            val betterX = Gdx.input.x.toFloat()/Gdx.graphics.width
            val betterY = Gdx.input.y.toFloat()/Gdx.graphics.height
            println("$betterX, $betterY")
        }
    }
}