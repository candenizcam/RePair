package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.pungo.repairgame.SharedVariables.activeScreen

class MouseManager{
    var pressed = false

    fun clickListener(){
        when {
            Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !pressed-> {
                pressed = true
                activeScreen.firstPress()
            }
            Gdx.input.isButtonPressed(Input.Buttons.LEFT) && pressed-> {
                activeScreen.pressing()
            }
            !Gdx.input.isButtonPressed(Input.Buttons.LEFT) && pressed-> {
                pressed = false
                activeScreen.released()
            }
        }
    }
}