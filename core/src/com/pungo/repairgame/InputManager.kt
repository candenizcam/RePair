package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class InputManager {
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
            Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && SharedVariables.activeScreen == SharedVariables.gameScreen -> {
                SharedVariables.gameScreen.pauseTimers()
                SharedVariables.activeScreen = SharedVariables.mainMenuScreen
            }
        }
    }
}