package com.pungo.repairgame.mainmenu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.Screen
import com.pungo.repairgame.SharedVariables

class MainMenuScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var startButton: SimpleButton
    private lateinit var continueButton: SimpleButton
    private lateinit var optionsButton: SimpleButton


    override fun lateInitializer() {
        mainSprite = SharedVariables.loadSprite(SharedVariables.mainMenuBackgroundPath, SharedVariables.menuBackgroundRatio)
        mainSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        mainSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        startButton = SimpleButton("graphics/menu_buttons/start", ratio = 0.5f)
        startButton.relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 500f)
        continueButton = SimpleButton("graphics/menu_buttons/continue", ratio = 0.5f)
        continueButton.relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 300f)
        optionsButton = SimpleButton("graphics/menu_buttons/options", ratio = 0.5f)
        optionsButton.relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 100f)
    }

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        startButton.draw(batch)
        continueButton.draw(batch)
        optionsButton.draw(batch)
    }

    override fun firstPress() {
        when {

            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), startButton.activeSprite) -> {
                startButton.status = MenuButtonStatus.DOWN
            }
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), continueButton.activeSprite) -> {
                continueButton.status = MenuButtonStatus.DOWN
            }
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), optionsButton.activeSprite) -> {
                optionsButton.status = MenuButtonStatus.DOWN
            }
        }
    }

    override fun pressing() {

        when {
            startButton.status == MenuButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), startButton.activeSprite)) startButton.status = MenuButtonStatus.UP
            }
            continueButton.status == MenuButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), continueButton.activeSprite)) continueButton.status = MenuButtonStatus.UP
            }
            optionsButton.status == MenuButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), optionsButton.activeSprite)) optionsButton.status = MenuButtonStatus.UP
            }
        }
    }

    override fun released() {
        when {
            startButton.status == MenuButtonStatus.DOWN -> {
                startButton.status = MenuButtonStatus.UP
                SharedVariables.activeScreen = SharedVariables.gameScreen
            }
            continueButton.status == MenuButtonStatus.DOWN -> {
                continueButton.status = MenuButtonStatus.UP
            }
            optionsButton.status == MenuButtonStatus.DOWN -> {
                optionsButton.status = MenuButtonStatus.UP
            }
        }
    }

    override fun loopAction() {

    }




}