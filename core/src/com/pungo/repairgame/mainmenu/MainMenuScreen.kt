package com.pungo.repairgame.mainmenu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.Screen
import com.pungo.repairgame.SharedVariables

class MainMenuScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var titleSprite: Sprite
    private lateinit var startButton: SimpleButton
    private lateinit var continueButton: SimpleButton
    private lateinit var optionsButton: SimpleButton


    override fun lateInitializer() {
        mainSprite = SharedVariables.loadSprite(SharedVariables.mainMenuBackgroundPath, SharedVariables.menuBackgroundRatio).apply{
            setCenterX(SharedVariables.mainWidth.toFloat() / 2)
            setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        }
        titleSprite = SharedVariables.loadSprite(SharedVariables.titlePath, 0.25).apply{
            setCenterX(SharedVariables.mainWidth.toFloat() / 2)
            setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        }
        startButton = SimpleButton("graphics/menu_buttons/start", ratio = 0.2f)
        startButton.relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 450f)
        continueButton = SimpleButton("graphics/menu_buttons/continue", ratio = 0.2f)
        continueButton.relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 300f)
        optionsButton = SimpleButton("graphics/menu_buttons/options", ratio = 0.2f)
        optionsButton.relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 150f)
    }

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        titleSprite.draw(batch)
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