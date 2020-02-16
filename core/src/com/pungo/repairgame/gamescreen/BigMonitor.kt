package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables

class BigMonitor {
    private var monitorSprite : Sprite
    private var monitorSideSprite : Sprite

    init{
        monitorSprite = SharedVariables.loadSprite(SharedVariables.monitorPath, SharedVariables.monitorRatio)
        monitorSprite.setCenterX(SharedVariables.monitorCentreX)
        monitorSprite.setCenterY(SharedVariables.monitorCentreY)
        monitorSideSprite = SharedVariables.loadSprite(SharedVariables.monitorFramePath, SharedVariables.monitorRatio)
        monitorSideSprite.setCenterX(SharedVariables.monitorCentreX)
        monitorSideSprite.setCenterY(SharedVariables.monitorCentreY)
    }

    fun draw(batch: SpriteBatch){
        monitorSprite.draw(batch)
        monitorSideSprite.draw(batch)
    }

    fun changeMonitor(path: String){
        monitorSprite = SharedVariables.loadSprite(path, SharedVariables.monitorRatio)
        monitorSprite.setCenterX(SharedVariables.monitorCentreX)
        monitorSprite.setCenterY(SharedVariables.monitorCentreY)
    }

}