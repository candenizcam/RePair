package com.pungo.repairgame.creditsscreen

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.Screen
import com.pungo.repairgame.SharedVariables
import com.pungo.repairgame.SharedVariables.loadSprite

class CreditsScreen : Screen() {
    private lateinit var bgSprite: Sprite
    private lateinit var creditsSprites: List<Sprite>
    private var indexer = 0.0



    override fun lateInitializer() {
        bgSprite = loadSprite("${SharedVariables.creditsPath}/background.png", SharedVariables.creditsRatio)
        bgSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        bgSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        var cs = mutableListOf<Sprite>()
        for (i in 1..6){
            cs.add(loadSprite("${SharedVariables.creditsPath}/${i}.png", SharedVariables.creditsRatio).apply {
                setCenter(SharedVariables.monitorCentreX,SharedVariables.monitorCentreY)
            }

            )
        }
        creditsSprites = cs.toList()
    }


    override fun draw(batch: SpriteBatch) {
        if (indexer>6.0){
            indexer = 0.0
            SharedVariables.mainMenuScreen.continueButton.visible = false
            SharedVariables.activeScreen = SharedVariables.mainMenuScreen
        } else{
            indexer += 0.0075
            if(indexer%1.0<0.1){
                // blank
            } else{
                creditsSprites[indexer.toInt()].draw(batch)
            }
        }
        bgSprite.draw(batch)
    }

    override fun firstPress() {
    }

    override fun pressing() {
    }

    override fun released() {
    }

    override fun loopAction() {
    }
}