package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MainMenuScreen : Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var bgPixmap: Pixmap
    private lateinit var startButton: SimpleButton
    private lateinit var continueButton: SimpleButton
    private lateinit var optionsButton: SimpleButton


    private fun loadImage() {
        val pixmap = Pixmap(Gdx.files.internal(SharedVariables.mainMenuBackgroundPath))
        bgPixmap = Pixmap(SharedVariables.mainWidth, SharedVariables.mainHeight, pixmap.format)
        bgPixmap.filter = Pixmap.Filter.NearestNeighbour
        bgPixmap.blending = Pixmap.Blending.None
        bgPixmap.drawPixmap(pixmap, 0, 0, pixmap.width, pixmap.height, 0, 0, bgPixmap.width, bgPixmap.height)
        pixmap.dispose()
        mainSprite = Sprite(Texture(bgPixmap))
        mainSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        mainSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        startButton = SimpleButton("graphics/placeholder_button", ratio = 0.5f)
        startButton.relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 500f)
        continueButton = SimpleButton("graphics/placeholder_button", ratio = 0.5f)
        continueButton.relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 300f)
        optionsButton = SimpleButton("graphics/placeholder_button", ratio = 0.5f)
        optionsButton.relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 100f)
    }


    override fun lateInitializer() {
        loadImage()
    }

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        startButton.draw(batch)
        continueButton.draw(batch)
        optionsButton.draw(batch)
    }

    override fun firstPress() {
        when {
            startButton.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) -> {
                startButton.pressed = true
            }
            continueButton.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) -> {
                continueButton.pressed = true
            }
            optionsButton.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) -> {
                optionsButton.pressed = true
            }
        }

        println("fp : ${Gdx.input.x} ${Gdx.input.y} ")
    }

    override fun pressing() {

        when {
            startButton.pressed -> {
                startButton.pressed = startButton.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
            }
            continueButton.pressed -> {
                continueButton.pressed = continueButton.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
            }
            optionsButton.pressed -> {
                optionsButton.pressed = optionsButton.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
            }
        }

        println("pr : ${Gdx.input.x} ${Gdx.input.y} ")
    }

    override fun released() {
        when {
            startButton.pressed -> {
                startButton.pressed = false
                println("Start go")

            }
            continueButton.pressed -> {
                continueButton.pressed = false
                println("Continue go")
            }
            optionsButton.pressed -> {
                optionsButton.pressed = false
                println("Options go")
            }
        }

        println("rel : ${Gdx.input.x} ${Gdx.input.y} ")
    }

    override fun loopAction() {

    }


}