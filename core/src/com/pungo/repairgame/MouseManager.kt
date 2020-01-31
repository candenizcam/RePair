package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class MouseManager(var activeScreen: Screen){

    fun checkMouse() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            Coordinates(Gdx.input.x, Gdx.input.y)
    }

    fun drawTentacle() {
        // draw tentacle at (Gdx.input.x, Gdx.input.y)
    }

    fun getClick() : Coordinates {
        return Coordinates(Gdx.input.x, Gdx.input.y)
    }
}