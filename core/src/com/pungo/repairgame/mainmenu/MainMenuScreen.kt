package com.pungo.repairgame.mainmenu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.Screen
import com.pungo.repairgame.SharedVariables

class MainMenuScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var StartButton : SimpleButton
    private lateinit var ContinueButton : SimpleButton
    private lateinit var OptionsButton : SimpleButton


    override fun lateInitializer() {
        mainSprite = SharedVariables.loadSprite(SharedVariables.mainMenuBackgroundPath, SharedVariables.menuBackgroundRatio)
        mainSprite.setCenterX(SharedVariables.mainWidth.toFloat()/2)
        mainSprite.setCenterY(SharedVariables.mainHeight.toFloat()/2)
        StartButton = SimpleButton("graphics/placeholder_button", ratio = 0.5f)
        StartButton.relocateCentre(SharedVariables.mainWidth.toFloat()/2,500f)
        ContinueButton = SimpleButton("graphics/placeholder_button", ratio = 0.5f)
        ContinueButton.relocateCentre(SharedVariables.mainWidth.toFloat()/2,300f)
        OptionsButton = SimpleButton("graphics/placeholder_button", ratio = 0.5f)
        OptionsButton.relocateCentre(SharedVariables.mainWidth.toFloat()/2,100f)
    }

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        StartButton.draw(batch)
        ContinueButton.draw(batch)
        OptionsButton.draw(batch)
    }

    override fun firstPress() {
        when {
            StartButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat()) -> {
                StartButton.status = MenuButtonStatus.DOWN
            }
            ContinueButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat()) -> {
                ContinueButton.status = MenuButtonStatus.DOWN
            }
            OptionsButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat()) -> {
                OptionsButton.status = MenuButtonStatus.DOWN
            }
        }

        println("fp : ${Gdx.input.x} ${Gdx.input.y} ")
    }

    override fun pressing() {

        when {
            StartButton.status == MenuButtonStatus.DOWN -> {
                if(!StartButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat())) StartButton.status = MenuButtonStatus.UP
            }
            ContinueButton.status == MenuButtonStatus.DOWN -> {
                if(!ContinueButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat())) ContinueButton.status = MenuButtonStatus.UP
            }
            OptionsButton.status == MenuButtonStatus.DOWN -> {
                if(!OptionsButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat())) OptionsButton.status = MenuButtonStatus.UP
            }
        }

        println("pr : ${Gdx.input.x} ${Gdx.input.y} ")
    }

    override fun released() {
        when {
            StartButton.status == MenuButtonStatus.DOWN -> {
                StartButton.status = MenuButtonStatus.UP
                SharedVariables.activeScreen = SharedVariables.gameScreen
                println("Start go")

            }
            ContinueButton.status == MenuButtonStatus.DOWN -> {
                ContinueButton.status = MenuButtonStatus.UP
                println("Continue go")
            }
            OptionsButton.status == MenuButtonStatus.DOWN -> {
                OptionsButton.status = MenuButtonStatus.UP
                println("Options go")
            }
        }

        println("rel : ${Gdx.input.x} ${Gdx.input.y} ")
    }

    override fun loopAction() {

    }




}